import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Collections;

public class CodeGenerator {

    public static void main(String[] args) {

        // ======================== 【配置区】 ========================

        // 1. 选择要生成代码的目标模块
        final String targetModule = "j-multicache-demo-code-generator";

        // 2. 目标模块的父包名
        final String parentPackage = "com.github.vevoly.demo.common";

        // 3. 要为哪些表生成代码
        final String[] tableNames = { "tenant", "user", "user_level", "user_avatar", "banner", "banner_position", "banner_user_type", "user_preference" };

        // 4. 数据库连接信息 (保持不变)
        final String DB_URL = "jdbc:mysql://localhost:3306/j_multicache_demo";
        final String DB_USERNAME = "root";
        final String DB_PASSWORD = "your_password";

        // ==========================================================

        // 计算项目根路径
        // user.dir 会指向父项目 multi-cache-utils-demo
        final String projectPath = System.getProperty("user.dir");
        final String outputDir = projectPath + "/" + targetModule + "/src/main/java";
        final String xmlOutputDir = projectPath + "/" + targetModule + "/src/main/resources/mapper";

        FastAutoGenerator.create(DB_URL, DB_USERNAME, DB_PASSWORD)
                .globalConfig(builder -> builder.author("demo-generator").outputDir(outputDir).disableOpenDir())
                .packageConfig(builder -> builder.parent(parentPackage).pathInfo(Collections.singletonMap(OutputFile.xml, xmlOutputDir)))
                .strategyConfig(builder -> builder.addInclude(tableNames)
                        .entityBuilder().enableLombok().enableTableFieldAnnotation()
                        .serviceBuilder().formatServiceFileName("%sService").formatServiceImplFileName("%sServiceImpl")
                        .mapperBuilder().enableBaseResultMap().enableBaseColumnList())
                .templateEngine(new VelocityTemplateEngine())
                .execute();

        System.out.println("代码已成功生成到模块: " + targetModule);
    }
}