package com;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;


/**
 * Created by wk.Feng on 2017/2/23.
 */
public class Generator {


    /**
     * <p>
     * MySQL
     * </p>
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        GlobalConfig gc = new GlobalConfig();
//        gc.setOutputDir("D:\\devData\\IdeaProjects\\skeleton\\src\\main\\java");
        gc.setOutputDir("D:\\develop\\equipment\\src\\main\\java");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("wk");

         gc.setMapperName("%sDao");
         gc.setXmlName("%sDao");
         gc.setServiceName("%sService");
         gc.setServiceImplName("%sServiceImpl");
         gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert(){
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                return super.processTypeConvert(fieldType);
            }
        });
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/cn_dhtbb?characterEncoding=utf8");
        mpg.setDataSource(dsc);

        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[]{"dh"});//
         strategy.setNaming(NamingStrategy.remove_prefix_and_camel);//
     // strategy.setNaming(NamingStrategy.removePrefixAndCamel());//
        strategy.setNaming(NamingStrategy.underline_to_camel);//
        strategy.setInclude(new String[] { "dh_test" }); //
      //  strategy.setExclude(new String[]{"t_user","t_role","t_permission"}); //
         strategy.setFieldNaming(NamingStrategy.underline_to_camel);
        strategy.setDbColumnUnderline(true);
        // strategy.setSuperEntityClass("com.fcs.demo.TestEntity");
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // strategy.setSuperMapperClass("com.fcs.demo.TestMapper");
        // strategy.setSuperServiceClass("com.fcs.demo.TestService");
        // strategy.setSuperServiceImplClass("com.fcs.demo.TestServiceImpl");
        // strategy.setSuperControllerClass("com.fcs.demo.TestController");
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuliderModel(true);
        mpg.setStrategy(strategy);

        PackageConfig pc = new PackageConfig();
        pc.setParent("com.dhwooden");
        pc.setModuleName("ep");
        pc.setController("controller");
        pc.setEntity("modal");
        pc.setMapper("mapper");
        pc.setXml("mapper.xml");

        mpg.setPackageInfo(pc);

//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                Map<String, Object> map = new HashMap<String, Object>();
//                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
//                this.setMap(map);
//            }
//        };
//        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
//        focList.add(new FileOutConfig("/template/list.jsp.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return "D://my_" + tableInfo.getEntityName() + ".jsp";
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);

        // TemplateConfig tc = new TemplateConfig();
        // tc.setController("...");
        // tc.setEntity("...");
        // tc.setMapper("...");
        // tc.setXml("...");
        // tc.setService("...");
        // tc.setServiceImpl("...");
        // mpg.setTemplate(tc);

        mpg.execute();

        //System.err.println(mpg.getCfg().getMap().get("abc"));
    }

}
