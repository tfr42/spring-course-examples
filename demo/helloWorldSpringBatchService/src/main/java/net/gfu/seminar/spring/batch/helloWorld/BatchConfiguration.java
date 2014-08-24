package net.gfu.seminar.spring.batch.helloWorld;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@EnableBatchProcessing
@PropertySource("classpath:/jdbc.properties")
public class BatchConfiguration {
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;

    @Bean
    public ItemReader<Guest> reader() {
        FlatFileItemReader<Guest> reader = new FlatFileItemReader<Guest>();
        reader.setResource(new ClassPathResource("sample-data.csv"));
        reader.setLineMapper(new DefaultLineMapper<Guest>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "firstName", "lastName" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Guest>() {{
                setTargetType(Guest.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ItemProcessor<Guest, Guest> processor() {
        return new GuestItemProcessor();
    }

    @Bean
    public ItemWriter<Guest> writer(DataSource dataSource) {
        JdbcBatchItemWriter<Guest> writer = new JdbcBatchItemWriter<Guest>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Guest>());
        writer.setSql("INSERT INTO guest (first_name, last_name) VALUES (:firstName, :lastName)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Job importUserJob(JobBuilderFactory jobs, Step s1) {
        return jobs.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(s1)
                .end()
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Guest> reader,
            ItemWriter<Guest> writer, ItemProcessor<Guest, Guest> processor) {
        return stepBuilderFactory.get("step1")
                .<Guest, Guest> chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
    
//    @Bean
//    public JobRepository jobRepository(DataSource dataSource) throws Exception {
//    	JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
//    	jobRepositoryFactoryBean.setDatabaseType("hsql");
//    	jobRepositoryFactoryBean.setDataSource(dataSource);
//    	//jobRepositoryFactoryBean.setIncrementerFactory(new HsqlMaxValueIncrementer(dataSource));
//    	return jobRepositoryFactoryBean.getJobRepository();
//    }
    
    @Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}