package com.pasier.goldroom.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class RmtShellExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(RmtShellExecutor.class);

    private Connection conn;
    private String ip;
    private String usr;
    private String psword;
    private String charset = Charset.defaultCharset().toString();

    private static final int TIME_OUT = 1000 * 5 * 60;

    public RmtShellExecutor(String ip, String usr, String ps) {
        this.ip = ip;
        this.usr = usr;
        this.psword = ps;
    }

    private boolean login() throws IOException {
        conn = new Connection(ip);
        conn.connect();
        return conn.authenticateWithPassword(usr, psword);
    }

    public String exec(String cmds) throws IOException {
        InputStream stdOut = null;
        InputStream stdErr = null;
        String outStr = "";
        String outErr = "";
        int ret = -1;

        try {
            if (login()) {
                Session session = conn.openSession();
                session.execCommand(cmds);
                stdOut = new StreamGobbler(session.getStdout());
                outStr = IOUtils.toString(stdOut, "UTF-8");
                LOG.info("caijl:[INFO] outStr=" + outStr);
                stdErr = new StreamGobbler(session.getStderr());
                outErr = processStream(stdErr, charset);
                LOG.info("caijl:[INFO] outErr=" + outErr);
                session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
                ret = session.getExitStatus();

            } else {
                LOG.error("caijl:[INFO] ssh2 login failure:" + ip);
                throw new IOException("SSH2_ERR");
            }

        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stdOut != null)
                stdOut.close();
            if (stdErr != null)
                stdErr.close();
        }
        return outStr.replaceAll("\n", "");
    }

    private String processStream(InputStream in, String charset) throws IOException {
        byte[] buf = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while (in.read(buf) != -1) {
            sb.append(new String(buf, charset));
        }
        return sb.toString();
    }

    // public static void main(String[] args) {
    //
    // String usr = "root";
    // String password = "Template@098";
    // String serverIP = "10.27.39.237";
    // String shPath = "/root/createtoken.sh";
    //
    // RmtShellExecutor exe = new RmtShellExecutor(serverIP, usr, password);
    //
    // String outInf;
    //
    // try {
    // outInf = exe.exec("sh " + shPath + " i-2-298-VM");
    // System.out.println("outInf= " + outInf);
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

}
