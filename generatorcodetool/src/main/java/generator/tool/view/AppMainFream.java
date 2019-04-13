package generator.tool.view;

import generator.tool.constants.CommonConstants;
import generator.tool.factory.GeneratorCodeUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * <p>Title: 工具主页面</p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/12/8
 */
public class AppMainFream {
    Logger logger = LoggerFactory.getLogger(AppMainFream.class);
    //初始化页面的方法
    public void inIntMainFream() {
        JFrame frame = new JFrame();
        frame.setTitle(CommonConstants.mainFreamTitle);
        frame.setSize(CommonConstants.mainFreamWidth, CommonConstants.mainFreamHight);
        //设置窗口位于屏幕中央
        frame.setLocationRelativeTo(null);
        //参数为3时，表示关闭窗口则程序退出
        frame.setDefaultCloseOperation(3);
        //1.2设置窗体上组件的布局，此处使用流式布局FlowLayout，流式布局类似于word的布局
        //用FlowLayout创建一个名为f1的对象,需要添加的包名为java.awt.FlowLayout，其中LEFT表示左对齐，CENTER表示居中对齐，RIGHT表示右对齐
        FlowLayout f1 = new FlowLayout(FlowLayout.LEFT);
        //frame窗口设置为f1的流式左对齐
        frame.setLayout(f1);
        JLabel name1 = new JLabel();
        name1.setPreferredSize(new Dimension(220, 30));
        frame.add(name1);
        JLabel name2 = new JLabel();
        name2.setPreferredSize(new Dimension(220, 30));
        frame.add(name2);
        //同上，此处添加的不是空JLabel，而是内容为“账号”的JLabel
        JLabel name = new JLabel(CommonConstants.cofigLabel);
        frame.add(name);
        //JTextField在窗口上添加一个可输入可见文本的文本框，需要添加的包名为javax.swing.JTextField.
        JTextField fileSrc = new JTextField();
        //设置文本框大小
        fileSrc.setPreferredSize(new Dimension(220, 30));
        //添加到窗口上
        frame.add(fileSrc);
        //JButton创建一个可点击的按钮，按钮上可显示文本图片
        JButton bu = new JButton(CommonConstants.generatorBtn);
        bu.setPreferredSize(new Dimension(100, 30));
        bu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (StringUtils.isEmpty(fileSrc.getText())) {
                    JOptionPane.showMessageDialog(null, "配置文件路径不能为空!", "标题", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        GeneratorCodeUtil generatorCodeUtil = new GeneratorCodeUtil(fileSrc.getText());
                        generatorCodeUtil.genterCodeFile();
                        JOptionPane.showMessageDialog(null, "代码生成成功!请到代码生成目录查看!", "标题", JOptionPane.INFORMATION_MESSAGE);
                    }catch (Exception e ){
                        logger.error(e.getMessage(),e);
                        String message = StringUtils.isEmpty(e .getMessage())?"":e .getMessage();
                        JOptionPane.showMessageDialog(null, "代码生成失败!错误信息：\n"+message+"\n具体错误请查看generator_code_log/generator_code_log.log日志", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        frame.add(bu);
        //设置窗口可见，此句一定要在窗口属性设置好了之后才能添加，不然无法正常显示
        frame.setVisible(true);
        frame.setResizable(false);
    }
}


