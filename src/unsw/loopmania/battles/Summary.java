package unsw.loopmania.battles;

import java.util.List;

public class Summary {
    private List<String> introduction;
    private List<String> body;
    private List<String> conclusion;

    public Summary(List<String> introduction, List<String> body,
            List<String> conclusion) {
        this.introduction = introduction;
        this.body = body;
        this.conclusion = conclusion;
    }

    /**
     * Returns the entire battle summary as one String
     * @return String containing battle summary
     */
    public String printMe() {
        return printIntroduction() + "\n" + printBody() + "\n" +
                printConclusion();
    }

    /**
     * Returns just the introduction as one String
     * @return String containing introduction
     */
    public String printIntroduction() {
        String intro = new String();

        for (String entry : introduction) {
            intro += entry + "\n";
        }

        return intro;
    }

    /**
     * Returns just the body as one String
     * @return String containing body
     */
    public String printBody() {
        String bodyParts = new String();

        int i = 0;
        for (String entry : body) {
            bodyParts += entry + "\n";

            // Add extra new line every two entries
            if (i % 2 == 1) {
                bodyParts += "\n";
            }

            i++;
        }

        return bodyParts.trim() + "\n";
    }

    /**
     * Returns just the conclusion as one String
     * @return String containing body
     */
    public String printConclusion() {
        String conc = new String();

        for (String entry : conclusion) {
            conc += entry + "\n";
        }

        return conc;
    }
}
