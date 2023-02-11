package com.xors.home.center.configuration;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.google.common.collect.Lists;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

/**
 * AbstractDruidConfiguration
 *
 * @author qingzhi
 * @desc 默认关系型数据库druid数据源连接池配置
 * @date 2018-12-12
 */
public abstract class AbstractDruidConfiguration {

    private static final String MYBATIS_CONFIG_PATH = "classpath:mybatis/mybatis-config.xml";
    private static final String TYPE_ALIASES_PACKAGE = "com.xors.home.center.dataobject";

    private AbstractDruidConfiguration() {
    }

    public static Properties druidProperties(Environment env, String prefix) {
        Properties prop = new Properties();
        //连接Schema名
        // prop.put("druid.deviceName", env.getProperty(prefix + "deviceName"));
        //连接字符串
        // prop.put("druid.url", env.getProperty(prefix + "url"));
        //用户名
        prop.put("druid.username", env.getProperty(prefix + "username"));
        //密码
        prop.put("druid.password", env.getProperty(prefix + "password"));
        //驱动名
        prop.put("druid.driverClassName", env.getProperty(prefix + "driver-class-name"));
        //初始化连接大小
        prop.put("druid.initialSize", env.getProperty(prefix + "initial-size"));
        //最大连接数
        prop.put("druid.maxActive", env.getProperty(prefix + "max-active"));
        //最小空闲连接数
        prop.put("druid.minIdle", env.getProperty(prefix + "min-idle"));

        druidBasicProperties(prop);

        return prop;
    }

    public static Properties druidBasicProperties(Properties prop) {
        //连接有效性验证
        prop.put("druid.validationQuery", "SELECT '1'");
        //连接有效性超时时间设置
        prop.put("druid.validationQueryTimeout", "1");

        //指明连接是否被空闲连接回收器(如果有)进行检验.如果检测失败,则连接将被从池中去除.
        prop.put("druid.testWhileIdle", "true");
        //指明是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        prop.put("druid.testOnBorrow", "true");
        //指明是否在归还到池中前进行检验
        prop.put("druid.testOnReturn", "false");
        //1) Destroy线程会检测连接的间隔时间 2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
        prop.put("druid.timeBetweenEvictionRunsMillis", "60000");

        //开启池的prepared statement 池功能,PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql5.5以下的版本中没有PSCache功能，建议关闭掉。5.5及以上版本有PSCache，建议开启。
        prop.put("druid.poolPreparedStatements", "true");
        //开启池的prepared statement 池功能
        prop.put("druid.maxOpenPreparedStatements", "100");

        prop.put("druid.useGlobalDataSourceStat", "true");
        return prop;
    }

    public static DataSource initDruidDataSource(String url, Environment env, String prefix) {
        DruidDataSource dataSource = new DruidDataSource();
        Properties prop = druidProperties(env, prefix);
        prop.setProperty("druid.url", url);
        dataSource.configFromPropety(prop);
        AbstractDruidConfiguration.setDefaultDruidProperties(dataSource);
        return dataSource;
    }

    public static void setDefaultDruidProperties(DruidDataSource dataSource) {
        StatFilter statFilter = new StatFilter();
        statFilter.setMergeSql(true);
        statFilter.setSlowSqlMillis(1000L);
        statFilter.setLogSlowSql(false);

        Slf4jLogFilter logFilter = new Slf4jLogFilter();
        logFilter.setConnectionLogEnabled(false);
        logFilter.setDataSourceLogEnabled(false);
        logFilter.setStatementLogEnabled(true);
        logFilter.setResultSetLogEnabled(false);
        logFilter.setStatementExecutableSqlLogEnable(true);

        WallFilter wallFilter = new WallFilter();
        wallFilter.setLogViolation(true);
        wallFilter.setThrowException(false);
        WallConfig wallConfig = new WallConfig();
        wallConfig.setDeleteWhereNoneCheck(true);
        wallFilter.setConfig(wallConfig);

        List<Filter> filters = Lists.newArrayList(statFilter, logFilter, wallFilter);

        dataSource.setProxyFilters(filters);

        //建立连接最大等待毫秒数
        dataSource.setMaxWait(60000L);
        //配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。
        dataSource.setUseUnfairLock(true);
        //对于长时间不使用的连接强制关闭
        dataSource.setRemoveAbandoned(true);
        //超过30分钟开始关闭空闲连接
        dataSource.setRemoveAbandonedTimeout(7200);
        //将当前关闭动作记录到日志
        dataSource.setLogAbandoned(true);
        dataSource.setQueryTimeout(10);
        dataSource.setKillWhenSocketReadTimeout(true);
    }

    public static SqlSessionFactory getSqlSessionFactory(DataSource dataSource, String xmlPath) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources(xmlPath));
        Properties prop = new Properties();
        bean.setConfigurationProperties(prop);
        bean.setConfigLocation(resolver.getResource(MYBATIS_CONFIG_PATH));
        //配置DO的默认包
        bean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
        //配置typeHandler的默认包
        return bean.getObject();
    }

}
