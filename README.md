# SpringBootLearning

It has 4 project which are working together.
1) APIGW - This is API gateway this is also Eurea client which will take API input and send to appropriate micro service to handle it.
2) CloudConfigurationServer - It has configuration which can be locally created and used.
3) EurecaServer - This is EurecaServer service 
4) ExperimentSpringBoot - This is Eurea Client with RestContoller and couchbase DB integration support, It also have example of how to use interceptor for http API to intercept API early to add common header before request process or response sent. How to use the service, repository, model classes. Also how we can access data from configuration file, this configuration file can be local or remote.

Before running this project you need to do some basic set up for couchbase DB integration : 
1) Download couchbase DB and create bucket in it and update bucket info in the ExperimentSpringBoot project
2) For Configuration we are accessing "welcome.message" in ExperimentSpringBoot so we need to create configuration file by the same name as of 
microservice LearningSpringBoot.properties file and set the value for the message and copy the message to the var/tmp directory if your in the mac.

In case if you don't want to have DB integration then go to controller package and change below service qualifier from productServiceDBImpl to productServiceImpl then locally data will be stored and used.
@Qualifier("productServiceDBImpl")
private ProductService productService;

To Run this project download the repo and run the project in following order.
1) EurecaServer
2) CloudConfigurationServer
3) APIGW
4) ExperimentSpringBoot

KafkaLearning : Is just independent project to understand how Kafka works.
