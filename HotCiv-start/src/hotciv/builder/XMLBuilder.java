package hotciv.builder;
/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 10-12-12
 * Time: 20:30
 * To change this template use File | Settings | File Templates.
 */
public class XMLBuilder implements Builder {
    private String result;
    public XMLBuilder() {
        result = new String();
    }

    @Override
    public void buildHeader(String text) {
        result += "<document name=''"+text+"''>"+"</document>\n";
    }
    @Override
    public void buildSection(String text) {
        result += "<section name=''"+text+"''>"+"</section>\n";
    }
    @Override
    public void buildSubsection(String text) {
        result += "<subsection name=''"+text+"''>"+"</subsection>\n";
    }
    @Override
    public void buildParagraph(String text) {
        result += "<paragraph>"+text + "</paragraph>\n";
    }

    public String getResult() {
        return result;
    }
}
