package hotciv.builder;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 10-12-12
 * Time: 20:28
 * To change this template use File | Settings | File Templates.
 */
public interface Builder {
    public void buildHeader(String text);
    public void buildSection(String text);
    public void buildSubsection(String text);
    public void buildParagraph(String text);

}