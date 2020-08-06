package com.dhwooden.ep.controller;


import com.alibaba.fastjson.JSONObject;
import com.dhwooden.ep.modal.User;
import com.dhwooden.ep.service.UserService;
import com.dhwooden.ep.util.EncryptUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.body.MultipartBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wk
 * @since 2018-04-18
 */
@Controller
@RequestMapping("/admin/danger")
public class DangerController {


}

