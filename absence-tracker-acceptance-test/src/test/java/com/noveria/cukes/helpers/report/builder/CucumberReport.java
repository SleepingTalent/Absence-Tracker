package com.noveria.cukes.helpers.report.builder;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noveria.cukes.helpers.report.XmlPrettyPrinter;

import cucumber.api.Scenario;

public class CucumberReport {

    private String output;
    private Scenario scenario;

    private CucumberReport(ReportBuilder builder, Scenario scenario) {
        this.output = builder.output;
        this.scenario = scenario;
    }

    public void write() {
        if(StringUtils.isNotEmpty(output)) {
            scenario.write(output);
        }
    }

    public static class ReportBuilder {

        private static final String NEW_LINE = "\n";

        private static int sectionId = 1;

        private static final String OUTPUT_PARENT_SECTION = "<a class='devView' style='display: block;' onclick=\"div=document.getElementById('%s'); div.style.display = (div.style.display == 'none' ? 'block' : 'none');return false\" href=\"\">%s</a>" +
                "<div id=\"%s\" class='devViewDetail' style='display:none;font-size:11px;max-width:auto; max-height:500px; overflow: auto;'></p>%s</div>";

        private static final String OUTPUT_SECTION = "<a class='devView' style='display: block; text-indent: 0px;' onclick=\"div=document.getElementById('%s'); div.style.display = (div.style.display == 'none' ? 'block' : 'none');return false\" href=\"\">%s</a>" +
                "<div id=\"%s\" class='devViewDetail' style='display:none;font-size:11px;max-width:auto; max-height:500px; overflow: auto;'><pre style='margin-bottom: 0;'>%s</pre></div>";

        private static final String HTML_TABLE_OUTPUT_SECTION = "<a class='devView' style='display:block; text-indent: 0px;' onclick=\"div=document.getElementById('%s'); div.style.display = (div.style.display == 'none' ? 'block' : 'none');return false\" href=\"\">%s</a>" +
                "<div id=\"%s\" class='devViewDetail' style='display:none;font-size:11px;max-width:auto; max-height:500px; overflow: auto;'></p>%s</div>";


        private String output = "";

        public ReportBuilder wrapWithinParentSection(String header) {
            generateParentOutput(OUTPUT_PARENT_SECTION, header, output);
            return this;
        }

        public ReportBuilder createSection(String header, String body) {
            generateOutput(OUTPUT_SECTION, header, body);
            return this;
        }

        public ReportBuilder createXmlSection(String header, String body) {
            String formatedXmlBody = formatXml(body);
            generateOutput(OUTPUT_SECTION, header,escapeHtml(formatedXmlBody + NEW_LINE));
            return this;
        }

        public ReportBuilder createJsonSection(String header, String body) throws IOException {
            String formatedJSonBody = formatJson(body);
            generateOutput(OUTPUT_SECTION, header,formatedJSonBody);
            return this;
        }

        public ReportBuilder createTableSection(String header, String body) {
            generateOutput(HTML_TABLE_OUTPUT_SECTION, header, body);
            return this;
        }

        private void generateOutput(String template, String header, String body) {
            if(StringUtils.isNotEmpty(body)) {
                String sectionId = generateNextSectionId(header);
                output += String.format(template, sectionId, escapeHtml(header), sectionId, body);
            }
        }

        private void generateParentOutput(String template, String header, String body) {
            if(StringUtils.isNotEmpty(body)) {
                String indentedBody = StringUtils.replace(body,"text-indent: 0px;","text-indent: 6px;");
                String sectionId = generateNextSectionId(header);
                output = String.format(template, sectionId, escapeHtml(header), sectionId, indentedBody);
            }
        }

        private String generateNextSectionId(String header) {
            String sectionIdString = header+"_"+sectionId;
            sectionId++;
            return sectionIdString;
        }

        private String formatXml(String body) {
            return XmlPrettyPrinter.prettyPrintXml(body);
        }

        private String formatJson(String input) throws IOException {
            if (StringUtils.isEmpty(input)) {
                return "N/A";
            }

            ObjectMapper mapper = new ObjectMapper();
            Object json = mapper.readValue(input, Object.class);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        }

        public CucumberReport build(Scenario scenario) {
           return new CucumberReport(this,scenario);
        }
    }
}
