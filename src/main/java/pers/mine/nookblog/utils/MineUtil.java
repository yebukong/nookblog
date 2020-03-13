package pers.mine.nookblog.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author yebukong
 * @description 工具类
 * @since 2018-10-23 10:37
 */
@Slf4j
public class MineUtil {
    private MineUtil() {
        throw new AssertionError("No MineUtil instances for you!");
    }

    /**
     * 获取本机的ＩＰ地址
     */
    public static String getLocalHostIP() {
        String result = "";
        try {
            InetAddress address = InetAddress.getLocalHost();
            result = address.getHostAddress();
        } catch (Exception e) {
            log.error("获取本机的IP地址异常", e);
        }
        return StringX.nvl(result, WebKit.UNKNOWN_MAGIC);
    }

    public static String getIPByName(String name) {
        String result = "";
        try {
            InetAddress address = InetAddress.getByName(name);
            result = address.getHostAddress();
        } catch (Exception e) {
            log.error("获取[" + name + "]IP地址异常", e);
        }
        return StringX.nvl(result, WebKit.UNKNOWN_MAGIC);
    }

    /**
     * [sun.desktop]
     * [awt.toolkit]
     * [file.encoding]
     * [file.encoding.pkg]
     * [file.separator]                          --文件分隔符（在 UNIX 系统中是“/”）
     * [idea.test.cyclic.buffer.size]
     * [java.awt.graphicsenv]
     * [java.awt.printerjob]
     * [java.class.path]                         --Java 类路径
     * [java.class.version]                      --Java 类格式版本号
     * [java.home]                               --Java 安装目录
     * [java.io.tmpdir]                          --默认的临时文件路径
     * [java.library.path]                       --加载库时搜索的路径列表
     * [java.runtime.name]
     * [java.runtime.version]
     * [java.specification.name]
     * [java.specification.vendor]
     * [java.specification.version]
     * [java.vendor]                             --Java 运行时环境供应商
     * [java.vendor.url]                         --Java 供应商的 URL
     * [java.vendor.url.bug]
     * [java.vendor.version]
     * [java.version]                            --Java 运行时环境版本
     * [java.version.date]
     * [java.vm.compressedOopsMode]
     * [java.vm.info]
     * [java.vm.name]
     * [java.vm.specification.name]              --Java 虚拟机规范名称
     * [java.vm.specification.vendor]            --Java 虚拟机规范供应商
     * [java.vm.specification.version]           --Java 虚拟机规范版本
     * [java.vm.vendor]                          --Java 虚拟机实现供应商
     * [java.vm.version]                         --Java 虚拟机实现版本
     * [jdk.debug]
     * [line.separator]                          --行分隔符（在 UNIX 系统中是“/n”）
     * [os.arch]                                 --操作系统的架构
     * [os.name]                                 --操作系统的名称
     * [os.version]                              --操作系统的版本
     * [path.separator]                          --路径分隔符（在 UNIX 系统中是“:”）
     * [sun.arch.data.model]
     * [sun.boot.library.path]
     * [sun.cpu.endian]
     * [sun.cpu.isalist]
     * [sun.desktop]
     * [sun.io.unicode.encoding]
     * [sun.java.command]
     * [sun.java.launcher]
     * [sun.jnu.encoding]
     * [sun.management.compiler]
     * [sun.os.patch.level]
     * [user.country]                            --用户的地区
     * [user.dir]                                --用户的当前工作目录
     * [user.home]                               --用户的主目录
     * [user.language]                           --用户的语言
     * [user.name]                               --用户的账户名称
     * [user.script]
     * [user.timezone]                           --用户的时区
     * [user.variant]
     */
    public static Map<String, String> getSystemInfoMap() {
        Properties sysProp = System.getProperties();
        Map<String, String> info = new HashMap<String, String>((Map) sysProp);
        return info;
    }

    /**
     * FreeMarker不支持带"."的变量名，需要将key格式转换
     * xxx.xxx -> xxxXXX
     */
    public static Map<String, String> getTrimKeySystemInfoMap() {
        Properties sysProp = System.getProperties();
        Map<String, String> info = new HashMap<String, String>();
        if (sysProp != null) {
            sysProp.forEach((k, v) -> {
                String e = k.toString();
                StringBuffer sbu = new StringBuffer();
                for (int i = 0; i < e.length(); i++) {
                    Character ch = e.charAt(i);
                    if (ch == '.') ch = null;
                    if (i > 0 && '.' == e.charAt(i - 1)) ch = Character.toUpperCase(ch);

                    if (ch != null) {
                        sbu.append(ch);
                    }
                }
                info.put(sbu.toString(), v == null ? "" : v.toString());
            });
        }
        return info;
    }
}
