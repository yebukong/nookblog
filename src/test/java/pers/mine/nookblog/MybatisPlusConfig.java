//package pers.mine.nookblog;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.core.MybatisConfiguration;
//import com.baomidou.mybatisplus.core.config.GlobalConfig;
//import com.baomidou.mybatisplus.core.config.GlobalConfig.DbConfig;
//import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
//import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import org.apache.ibatis.plugin.Interceptor;
//import org.mybatis.spring.mapper.MapperScannerConfigurer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//
//import java.util.ArrayList;
//import java.util.List;
//
////@Configuration
//public class MybatisPlusConfig {
//
//    // 环境标志, 区分dev or prod
//    @Autowired
//    private String projectStage;
//
//    /**
//     * 数据源a 相关信息
//     */
//    @Value("${a.url}")
//    private String aUrl;
//
//    @Value("${a.username}")
//    private String aUsername;
//
//    @Value("${a.password}")
//    private String aPassword;
//
//    /**
//     * 数据源b 相关信息
//     */
//    @Value("${b.url}")
//    private String bUrl;
//
//    @Value("${b.username}")
//    private String bUsername;
//
//    @Value("${b.password}")
//    private String bPassword;
//
//    // 创建数据源a
//    @Bean(initMethod = "init", destroyMethod = "close")
//    public DruidDataSource aDataSource(){
//        DruidDataSource d = new DruidDataSource();
//        d.setUrl(aUrl);
//        d.setUsername(aUsername);
//        d.setPassword(aPassword);
//        return d;
//    }
//
//    @Bean(initMethod = "init", destroyMethod = "close")
//    public DruidDataSource bDataSource(){
//        DruidDataSource d= new DruidDataSource();
//        d.setUrl(bUrl);
//        d.setUsername(bUsername);
//        d.setPassword(bPassword);
//        return d;
//    }
//
//    // 创建全局配置
//    @Bean
//    public GlobalConfig mpGlobalConfig() {
//        // 全局配置文件
//        GlobalConfig globalConfig = new GlobalConfig();
//        DbConfig dbConfig = new DbConfig();
//        // 默认为自增
//        dbConfig.setIdType(IdType.AUTO);
//        // 手动指定db 的类型, 这里是mysql
//        dbConfig.setDbType(DbType.MYSQL);
//        globalConfig.setDbConfig(dbConfig);
////        if (!ProjectStageUtil.isProd(projectStage)) {
////            // 如果是dev环境,则使用 reload xml的功能,方便调试
////            globalConfig.setRefresh(true);
////        }
//        // 逻辑删除注入器
//        LogicSqlInjector injector = new LogicSqlInjector();
//        globalConfig.setSqlInjector(injector);
//        return globalConfig;
//    }
//
//    @Bean(name = "aSqlSessionFactory")
//    public MybatisSqlSessionFactoryBean aSqlSessionFactory(
//            DruidDataSource aDataSource,
//            GlobalConfig globalConfig) {
//        return getSessionFactoryBean(aDataSource, globalConfig);
//    }
//
//    /**
//     * MapperScannerConfigurer 是 BeanFactoryPostProcessor 的一个实现，如果配置类中出现 BeanFactoryPostProcessor ，会破坏默认的
//     * post-processing, 如果不加static, 会导致整个都提前加载, 这时候, 取不到projectStage的值
//     *
//     * @return
//     */
//    @Bean
//    public static MapperScannerConfigurer aMapperScannerConfigurer() {
//        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
//        configurer.setBasePackage("com.a");
//        // 设置为上面的 factory name
//        configurer.setSqlSessionFactoryBeanName("bSqlSessionFactory");
//        return configurer;
//    }
//
//    @Bean(name = "bSqlSessionFactory")
//    public MybatisSqlSessionFactoryBean bSqlSessionFactory(
//            DruidDataSource bDataSource,
//            GlobalConfig mpGlobalConfig) {
//        return getSessionFactoryBean(bDataSource, mpGlobalConfig);
//    }
//
//    private MybatisSqlSessionFactoryBean getSessionFactoryBean(
//            aDataSource aDataSource,
//            GlobalConfig globalConfig) {
//        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(aDataSource);
//        sqlSessionFactoryBean.setGlobalConfig(globalConfig);
//        // 源码里面如果有configuration, 不会注入BaseMapper里面的方法, 所以这里要这样写
//        MybatisConfiguration configuration = new MybatisConfiguration().init(globalConfig);
//        configuration.setMapUnderscoreToCamelCase(true);
//        sqlSessionFactoryBean.setConfiguration(configuration);
//        List<Interceptor> interceptors = new ArrayList<>();
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        // 设置分页插件
//        interceptors.add(paginationInterceptor);
//        if (!ProjectStageUtil.isProd(projectStage)) {
//            // 如果是dev环境,打印出sql, 设置sql拦截插件, prod环境不要使用, 会影响性能
//            PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
//            interceptors.add(performanceInterceptor);
//        }
//        sqlSessionFactoryBean.setPlugins(interceptors.toArray(new Interceptor[0]));
//        return sqlSessionFactoryBean;
//    }
//
//    /**
//     * b 的mapperscan
//     * @return
//     */
//    @Bean
//    public static MapperScannerConfigurer bMapperScannerConfigurer() {
//        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
//        configurer.setBasePackage("com.b");
//        // 设置为上面的 factory name
//        configurer.setSqlSessionFactoryBeanName("bSqlSessionFactory");
//        return configurer;
//    }
//}