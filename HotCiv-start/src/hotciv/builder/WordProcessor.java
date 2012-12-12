package hotciv.builder;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 10-12-12
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */
public class WordProcessor {

    private String header = "The Builder Pattern";
    private String section = "Builder";
    private String paragraph1 =
            "This is my section on builder\n";
    private String subSection = "Analysis";
    private String paragraph2 = "This is my analysis of builder";

    public void construct(Builder builder) {
        /* a real constructor would iterate over a
 * data structure, here I have hardcoded the
 * document to keep the code small */
        builder.buildHeader(header);
        builder.buildSection(section);
        builder.buildParagraph(paragraph1);
        builder.buildSubsection(subSection);
        builder.buildParagraph(paragraph2);
    }

}