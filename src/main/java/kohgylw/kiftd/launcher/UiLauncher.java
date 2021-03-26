package kohgylw.kiftd.launcher;

import com.formdev.flatlaf.FlatDarculaLaf;
import kohgylw.kiftd.printer.Out;
import kohgylw.kiftd.server.ctl.DiskCtl;
import kohgylw.kiftd.server.enumeration.LogLevel;
import kohgylw.kiftd.server.enumeration.VCLevel;
import kohgylw.kiftd.server.pojo.ExtendStores;
import kohgylw.kiftd.server.util.ConfigureReader;
import kohgylw.kiftd.server.util.ServerTimeUtil;
import kohgylw.kiftd.ui.callback.GetServerStatus;
import kohgylw.kiftd.ui.module.ServerUiModule;
import kohgylw.kiftd.ui.pojo.FileSystemPath;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>UI界面模式启动器</h2>
 * <p>
 * 该启动器将以界面模式启动kiftd，请执行静态build()方法开启界面并初始化kiftd服务器引擎。
 * </p>
 *
 * @author 青阳龙野(kohgylw)
 * @version 1.0
 */
public class UiLauncher
{

    private volatile static UiLauncher instance;

    /**
     * 实例化图形界面并显示它，同时将图形界面的各个操作与服务器控制器对应起来;
     * @throws Exception
     */
    private UiLauncher() throws Exception
    {
        initSkin();
        Out.putModel(true);
        final ServerUiModule ui = ServerUiModule.getInstance();
        // 服务器控制层，用于连接UI与服务器内核;
        DiskCtl ctl = new DiskCtl();
        ServerUiModule.setStartServer(() -> ctl.start());
        ServerUiModule.setOnCloseServer(() -> ctl.stop());
        ServerUiModule.setGetServerTime(() -> ServerTimeUtil.getServerTime());
        ServerUiModule.setGetServerStatus(new GetServerStatus()
        {
            @Override
            public boolean getServerStatus()
            {
                // TODO 自动生成的方法存根
                return ctl.started();
            }

            @Override
            public int getPropertiesStatus()
            {
                // TODO 自动生成的方法存根
                return ConfigureReader.getInstance().getPropertiesStatus();
            }

            @Override
            public int getPort()
            {
                // TODO 自动生成的方法存根
                return ConfigureReader.getInstance().getPort();
            }

            @Override
            public boolean getMustLogin()
            {
                // TODO 自动生成的方法存根
                return ConfigureReader.getInstance().mustLogin();
            }

            @Override
            public LogLevel getLogLevel()
            {
                // TODO 自动生成的方法存根
                return ConfigureReader.getInstance().getLogLevel();
            }

            @Override
            public String getFileSystemPath()
            {
                // TODO 自动生成的方法存根
                return ConfigureReader.getInstance().getFileSystemPath();
            }

            @Override
            public int getBufferSize()
            {
                // TODO 自动生成的方法存根
                return ConfigureReader.getInstance().getBuffSize();
            }

            @Override
            public VCLevel getVCLevel()
            {
                // TODO 自动生成的方法存根
                return ConfigureReader.getInstance().getVCLevel();
            }

            @Override
            public List<FileSystemPath> getExtendStores()
            {
                List<FileSystemPath> fsps = new ArrayList<FileSystemPath>();
                for (ExtendStores es : ConfigureReader.getInstance().getExtendStores())
                {
                    FileSystemPath fsp = new FileSystemPath();
                    fsp.setIndex(es.getIndex());
                    fsp.setPath(es.getPath());
                    fsp.setType(FileSystemPath.EXTEND_STORES_NAME);
                    fsps.add(fsp);
                }
                return fsps;
            }

            @Override
            public LogLevel getInitLogLevel()
            {
                // TODO 自动生成的方法存根
                return ConfigureReader.getInstance().getInitLogLevel();
            }

            @Override
            public VCLevel getInitVCLevel()
            {
                // TODO 自动生成的方法存根
                return ConfigureReader.getInstance().getInitVCLevel();
            }

            @Override
            public String getInitFileSystemPath()
            {
                // TODO 自动生成的方法存根
                return ConfigureReader.getInstance().getInitFileSystemPath();
            }

            @Override
            public String getInitProt()
            {
                // TODO 自动生成的方法存根
                return ConfigureReader.getInstance().getInitPort();
            }

            @Override
            public String getInitBufferSize()
            {
                // TODO 自动生成的方法存根
                return ConfigureReader.getInstance().getInitBuffSize();
            }

            @Override
            public boolean isAllowChangePassword()
            {
                // TODO 自动生成的方法存根
                return ConfigureReader.getInstance().isAllowChangePassword();
            }

            @Override
            public boolean isOpenFileChain()
            {
                // TODO 自动生成的方法存根
                return ConfigureReader.getInstance().isOpenFileChain();
            }

            @Override
            public int getMaxExtendStoresNum()
            {
                // TODO 自动生成的方法存根
                return ConfigureReader.getInstance().getMaxExtendstoresNum();
            }
        });
        ServerUiModule.setUpdateSetting(s ->
        {
            // TODO 自动生成的方法存根
            return ConfigureReader.getInstance().doUpdate(s);
        });
        ui.show();
    }

    public static UiLauncher getInstance() throws Exception
    {
        if (instance == null)
        {
            synchronized (UiLauncher.class)
            {
                if (instance == null)
                {
                    instance = new UiLauncher();
                }
            }
        }
        return instance;
    }

    /**
     * <h2>以UI模式运行kiftd</h2>
     * <p>
     * 执行该方法后，kiftd将立即显示服务器主界面（需要操作系统支持图形界面）并初始化服务器引擎，等待用户点击按钮并触发相应的操作。
     * 该方法将返回本启动器的唯一实例。
     * </p>
     *
     * @return kohgylw.kiftd.mc.UIRunner 本启动器唯一实例
     * @throws Exception
     * @author 青阳龙野(kohgylw)
     */
    public static UiLauncher build() throws Exception
    {
        return getInstance();
    }

    private void initSkin()
    {
        try
        {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        }
        catch (UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }
    }
}
