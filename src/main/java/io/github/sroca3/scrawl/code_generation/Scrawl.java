package io.github.sroca3.scrawl.code_generation;

import freemarker.template.Configuration;
import freemarker.template.SimpleSequence;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.github.sroca3.scrawl.configuration.ScrawlConfiguration;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Scrawl {
    public static void generate(String configurationFilePath) throws IOException, TemplateException {
        URL resource = Scrawl.class.getClassLoader().getResource("templates");
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_29);
        configuration.setDirectoryForTemplateLoading(new File(resource.getFile()));
        Template template = configuration.getTemplate("table.ftl");
        Writer out = new FileWriter("src/test/java/io/github/sroca3/scrawl/sqlserver/schema/CityTable.java");

        Yaml yaml = new Yaml(new Constructor(ScrawlConfiguration.class));
        ScrawlConfiguration scrawlConfiguration = yaml.load(Scrawl.class.getClassLoader()
                                                                        .getResourceAsStream(configurationFilePath));

        Map<String, Object> dataModel = new HashMap<>();

        scrawlConfiguration.getScrawl().getTables()
                           .forEach((key, value) -> {
                               dataModel.put("tableName", key);
                               SimpleSequence columns = new SimpleSequence(configuration.getObjectWrapper());
                               value.forEach(column -> {
                                   columns.add(column);
                               });
                               dataModel.put("columns", columns);
                           });

//        dataModel.put("tableName", "City");
//        SimpleSequence columns = new SimpleSequence(configuration.getObjectWrapper());
//        columns.add("Id");
//        columns.add("Name");
//        dataModel.put("columns", columns);
        template.process(dataModel, out);
    }
}
