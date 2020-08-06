package com.dhwooden.ep.shiro;

import com.dhwooden.ep.mapper.UserDao;
import com.dhwooden.ep.modal.DhLogin;
import com.dhwooden.ep.modal.User;
import com.dhwooden.ep.service.DhLoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kaiwang on 2018/7/17.
 */
public class ShiroRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userService;

    @Autowired
    private DhLoginService dhLoginService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("doGetAuthorizationInfo+" + principalCollection.toString());
        //  User user = userService.getByUserName((String) principalCollection.getPrimaryPrincipal());
        String s=principalCollection.getPrimaryPrincipal().toString();
        //DhLogin lo= (DhLogin) principalCollection.getPrimaryPrincipal();
        Map map=new HashMap<>();
        map.put("username",s);
        //map.put("pwd",lo.getPwd());

        List<DhLogin> list=dhLoginService.selectByMap(map);
       String per=list.get(0).getPer();
        String role=list.get(0).getRole();
        //把principals放session中 key=userId value=principals
        SecurityUtils.getSubject().getSession().setAttribute(String.valueOf("id"), SecurityUtils.getSubject().getPrincipals());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
         logger.info("doGetAuthorizationInfo give role+entry" + principalCollection.toString());
         info.addRole(role);
         logger.info("doGetAuthorizationInfo give  Permission+entry:list" + principalCollection.toString());
         info.addStringPermission(per);
        //赋予角色
//        for(Role userRole:user.getRoles()){
//            info.addRole(userRole.getName());
//        }
        //赋予权限
//        for(Permission permission:permissionService.getByUserId(user.getId())){
////            if(StringUtils.isNotBlank(permission.getPermCode()))
//            info.addStringPermission(permission.getName());
//        }

        //设置登录次数、时间
//        userService.updateUserLogin(user);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("doGetAuthenticationInfo +" + authenticationToken.toString());

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        String pwd = token.getPassword().toString();
        logger.info(userName + token.getPassword());
        Map map=new HashMap<>();
        map.put("username",userName);
        //map.put("pwd",pwd);
        List<DhLogin> list=dhLoginService.selectByMap(map);
        //User user = userService.getByUserName(token.getUsername());
        if (list != null) {
            DhLogin lo=list.get(0);
            String per=lo.getPer();
//            byte[] salt = Encodes.decodeHex(user.getSalt());
         // ShiroUser shiroUser=new ShiroUser(user.getId(), user.getLoginName(), user.getName());
            //设置用户session
            Session session = SecurityUtils.getSubject().getSession();
            SecurityUtils.getSubject().getSession().setTimeout(1800000);
            session.setAttribute("username", userName);
            return new SimpleAuthenticationInfo(userName,lo.getPwd(), getName());
        } else {
            return null;
        }
//        return null;
    }

}