package com.matching;
import com.utils.Utils;
import org.junit.jupiter.api.Test;

import static com.matching.fgpdg.Configurations.PROJECT_REPOSITORY;


public class DetectPattern {

    @Test
    void testPattern() throws Exception {
        String pattern = "# type number : Any\n" +
                "# type :[l3] : List[int]\n" +
                "# type :[[l1]] : int\n" +
                "# type :[[l2]] : int\n" +
                ":[[l1]] = 0\n" +
                "for :[[l2]] in :[l3]:\n" +
                "   :[[l1]]=:[[l1]]+:[[l2]]";
        String outPath = "./OUTPUT/"; //https://github.com/maldil/MLEditsTest.git
        String projectPath =  PROJECT_REPOSITORY +"pythonInfer/PatternTest";
        System.out.println(pattern);
        Utils.processProjectForPattern(projectPath,pattern,outPath);
    }
}
