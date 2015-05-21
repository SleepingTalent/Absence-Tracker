package com.noveria.cukes.helpers.report;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.codec.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.Scenario;

@Component("scenarioHelper")
@ContextConfiguration(locations = { "classpath:cucumber.xml" })
public class ScenarioHelper {

    public void showDeveloperBusinessToggle(Scenario scenario) throws IOException {
            scenario.write(IOUtils.toString(getClass().getResourceAsStream("/report/report.fragment.html")));
            //scenario.write(IOUtils.toString(getClass().getResourceAsStream("/report/report.fragment.html"), Charsets.UTF_8));
    }

    public <T> String generateHtmlTable(String caption,  List<T> list, Class<T> clazz) throws IllegalAccessException {
        String tableStart = "<table class=\'data-table\' style=\'width:auto;\'>";
        String tableEnd = "</table>";

        String columnHeaders = generateColumnHeaders(clazz.getDeclaredFields());
        StringBuilder tableData = new StringBuilder();

        for(T listItem : list){
            tableData.append(addTableRow(listItem, clazz.getDeclaredFields()));
        }

        StringBuilder htmlTable = new StringBuilder();

        htmlTable.append(tableStart);

        if(StringUtils.isNotEmpty(caption)) {
            htmlTable.append("<caption>");
            htmlTable.append(caption);
            htmlTable.append("</caption>");
        }

        htmlTable.append(columnHeaders);
        htmlTable.append(tableData);
        htmlTable.append(tableEnd);

        return htmlTable.toString();
    }

    private String addTableRow(Object object, Field[] declaredFields) throws IllegalAccessException {
        StringBuilder tableRow = new StringBuilder();
        tableRow.append("<tr>");

        for (Field field : declaredFields) {
            tableRow.append("<td style='white-space:nowrap;'>");

            field.setAccessible(true);

            if (field.get(object) != null) {
                tableRow.append(field.get(object).toString());
            } else {
                tableRow.append("");
            }

            tableRow.append("</td>");
        }

        tableRow.append("</tr>");

        return tableRow.toString();
    }

    private String generateColumnHeaders(Field[] declaredFields) {
        StringBuilder columnHeaders = new StringBuilder();
        columnHeaders.append("<tr>");

        for (Field field : declaredFields) {
            columnHeaders.append("<th>");
            columnHeaders.append(field.getName());
            columnHeaders.append("</th>");
        }

        columnHeaders.append("</tr>");

        return columnHeaders.toString();
    }
}
