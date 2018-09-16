package generator;

import generator.tool.factory.GeneratorCodeUtil;

/**
 * 主启动类
 */
public class AppMain {


    /**
     * @param args
     */
    public static void main(String[] args) {
        GeneratorCodeUtil generatorCodeUtil = new GeneratorCodeUtil();
        generatorCodeUtil.genterCodeFile();
    }

}