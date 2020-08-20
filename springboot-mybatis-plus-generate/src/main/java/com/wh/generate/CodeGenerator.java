package com.wh.generate;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * <p>
 * 代码生成，默认生成在项目的根目录下
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/19
 */
public class CodeGenerator {

    public static void main(String[] args) {

        // 生成的包名
        String packageName = "com.wh.db";
        // user -> UserService, 设置成true: user -> IUserService
        boolean serviceNameStartWithI = false;
        generateByTables(serviceNameStartWithI, packageName, "student");
    }

    /**
     * 代码生成
     *
     * @param serviceNameStartWithI 是否生成带 I 前缀
     * @param packageName           包名
     * @param tableNames            表名
     */
    private static void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();

        // 生成代码的目录
        String projectPath = System.getProperty("user.dir");
        config.setOutputDir(projectPath + "/src/main/java");

        String dbUrl = "jdbc:mysql://127.0.0.1:3306/demo1?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai";
        // 数据库配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig().setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("root")
                .setDriverName("com.mysql.jdbc.Driver");

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig().setCapitalMode(true)
                // 是否使用 Lombok
                .setEntityLombokModel(true)
                // 字段下划线转驼峰
                .setNaming(NamingStrategy.underline_to_camel)
                // 修改替换成你需要的表名，多个表名传数组
                .setInclude(tableNames);

        config.setAuthor("Wenhao Wang").setSwagger2(true)
                .setOutputDir(projectPath)
                .setIdType(IdType.INPUT)
                .setFileOverride(true);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }

        // 包名设置
        PackageConfig packageConfig = new PackageConfig()
                .setParent(packageName);

        // 生成代码
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .execute();
    }
}