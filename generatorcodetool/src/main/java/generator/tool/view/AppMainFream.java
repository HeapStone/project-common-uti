package generator.tool.view;

import freemarker.template.utility.StringUtil;
import generator.tool.constants.CommonConstants;
import generator.tool.factory.FeatureService;
import generator.tool.factory.GeneratorCodeUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.prefs.Preferences;


/**
 * <p>Title: 工具主页面</p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/12/8
 */
public class AppMainFream {
    Font font = new Font("微软雅黑", Font.PLAIN, 20);
    Logger logger = LoggerFactory.getLogger(AppMainFream.class);

    //初始化页面的方法
    public void inIntMainFream() {
        logger.info(" ----------------------start begin");
        JFrame frame = new JFrame();
        frame.setTitle(CommonConstants.mainFreamTitle);
        frame.setSize(CommonConstants.mainFreamWidth, CommonConstants.mainFreamHight);
        //设置窗口位于屏幕中央
        frame.setLocationRelativeTo(null);
        //参数为3时，表示关闭窗口则程序退出
        frame.setDefaultCloseOperation(3);
        //1.2设置窗体上组件的布局，此处使用流式布局FlowLayout，流式布局类似于word的布局
        //用FlowLayout创建一个名为f1的对象,需要添加的包名为java.awt.FlowLayout，其中LEFT表示左对齐，CENTER表示居中对齐，RIGHT表示右对齐
        BorderLayout f1 = new BorderLayout();
        //frame窗口设置为f1的流式左对齐
        frame.setLayout(f1);
        JPanel top = new JPanel();
        Label l1 = new Label();
        l1.setSize(new Dimension(600, 600));
        top.add(l1);
        JPanel center = new JPanel();
        JPanel bottom = new JPanel();

        JTextArea textArea = new JTextArea();
        textArea.setSize(new Dimension(800, 200));
        textArea.setLineWrap(true);        //激活自动换行功能
        textArea.setText("代码生成日志信息\n");
        textArea.setFont(font);
        center.add(textArea);
        addLabel(top);
        //JTextField在窗口上添加一个可输入可见文本的文本框，需要添加的包名为javax.swing.JTextField.
        JTextField fileSrc = addFieldText(top);
        //JButton创建一个可点击的按钮，按钮上可显示文本图片
        addBtu(bottom, fileSrc, textArea);
        //设置窗口可见，此句一定要在窗口属性设置好了之后才能添加，不然无法正常显示
        frame.setVisible(true);
        frame.add(top, BorderLayout.NORTH);
        frame.add(center, BorderLayout.CENTER);
        frame.add(bottom, BorderLayout.SOUTH);
        frame.setResizable(false);
        logger.info("---------------start success ！");
    }

    private void addBtu(JPanel frame, JTextField fileSrc, JTextArea textArea) {
        JButton buChose = new JButton(CommonConstants.choseBtn);
        buChose.setPreferredSize(new Dimension(200, 30));
        buChose.setFont(font);
        buChose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int result = 0;
                JFileChooser fileChooser = new JFileChooser();
                String path = System.getProperty("user.dir");
                if (StringUtils.isNotEmpty(fileSrc.getText())) {
                    path = fileSrc.getText();
                }
                fileChooser.setCurrentDirectory(new File(path));
                FileFilter filter = new FileNameExtensionFilter("xml文件（XML）", "XML");
                fileChooser.setFileFilter(filter);
                fileChooser.setVisible(true);
                fileChooser.setFont(font);
                fileChooser.setDialogTitle("请选择生成代码的配置文件...");
                fileChooser.setApproveButtonText("确定");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                result = fileChooser.showOpenDialog(frame);
                if (JFileChooser.APPROVE_OPTION == result) {
                    path = fileChooser.getSelectedFile().getPath();
                    fileSrc.setText(path);
                    fileSrc.setToolTipText(path);
                }
            }
        });
        frame.add(buChose, BorderLayout.EAST);
        //JButton创建一个可点击的按钮，按钮上可显示文本图片
        JButton btuConfirm = new JButton(CommonConstants.generatorBtn);
        btuConfirm.setPreferredSize(new Dimension(200, 30));
        btuConfirm.setFont(font);
        btuConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (StringUtils.isEmpty(fileSrc.getText())) {
                    JOptionPane.showMessageDialog(null, "配置文件路径不能为空!", "标题", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        printLog("读取配置文件:" + fileSrc.getText() + "的配置信息", textArea);
                        printLog("正在生成代码文件，请稍后。。。。。。。", textArea);
                        btuConfirm.setEnabled(false);
                        buChose.setEnabled(false);
                        FeatureService<Boolean> featureService = new FeatureService<Boolean>(1);
                        featureService.submit(() -> {
                            long beginTime = System.currentTimeMillis();
                            logger.info("开启多线程处理业务");
                            GeneratorCodeUtil generatorCodeUtil = new GeneratorCodeUtil(fileSrc.getText());
                            boolean res = generatorCodeUtil.genterCodeFile();
                            btuConfirm.setEnabled(true);
                            buChose.setEnabled(true);
                            long endTime = System.currentTimeMillis();
                            if(res){
                                printLog("代码生成成功共耗时["+(endTime-beginTime)+"]毫秒", textArea);
                            }else{
                                printLog("代码生成失败共耗时["+(endTime-beginTime)+"]毫秒", textArea);
                            }
                            return res;
                        });
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        String message = StringUtils.isEmpty(e.getMessage()) ? "" : e.getMessage();
                        printLog(message, textArea);
                        JOptionPane.showMessageDialog(null, "代码生成失败!错误信息：\n" + message + "\n具体错误请查看generator_code_log/generator_code_log.log日志", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }


        });
        frame.add(btuConfirm, BorderLayout.SOUTH);
    }

    private JTextField addFieldText(JPanel frame) {
        // 文件路径选择框
        JTextField fileSrc = new JTextField();
        //设置文本框大小
        fileSrc.setPreferredSize(new Dimension(760, 30));
        fileSrc.setToolTipText(fileSrc.getText());
        fileSrc.setFont(font);
        //添加到窗口上
        frame.add(fileSrc, BorderLayout.CENTER);
        return fileSrc;
    }

    private void addLabel(JPanel frame) {
        // 文件路径label
        JLabel name = new JLabel(CommonConstants.cofigLabel);
        name.setFont(font);
        frame.add(name, BorderLayout.WEST);
    }

    private void printLog(String s, JTextArea textArea) {
        textArea.paintImmediately(textArea.getBounds());
        textArea.append(s + "\n");
    }
}


