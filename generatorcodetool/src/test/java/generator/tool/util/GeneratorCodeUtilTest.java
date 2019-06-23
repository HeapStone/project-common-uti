package generator.tool.util;

import generator.tool.factory.GeneratorCodeUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>Title: </p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2019/6/23
 */

public class GeneratorCodeUtilTest {
    @Test
    public void testgenterCodeFile(){
        GeneratorCodeUtil generatorCodeUtil = new GeneratorCodeUtil(
                this.getClass().getResource("/codeFile-cfg.xml").getFile());
        Assert.assertTrue(generatorCodeUtil.genterCodeFile());
    }
}
