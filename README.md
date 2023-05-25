# SpringBootLearning

It has 4 project which are working together.
1) APIGW - This is API gateway this is also Eurea client which will take API input and send to appropriate micro service to handle it.
2) CloudConfigurationServer - It has configuration which can be locally created and used.
3) EurecaServer - This is EurecaServer service 
4) ExperimentSpringBoot - This is Eurea Client with RestContoller and couchbase DB integration support. 

Before running this project you need to do some basic set up for couchbase DB integration : 
1) Download couchbase DB and create bucket in it and update bucket info in the ExperimentSpringBoot project
2) For Configuration we are accessing "welcome.message" in ExperimentSpringBoot so we need to create configuration file by the same name as of 
microservice LearningSpringBoot.properties file and set the value for the message and copy the message to the var/tmp directory if your in the mac.

To Run this project download the repo and run the project in following order.
1) EurecaServer
2) CloudConfigurationServer
3) APIGW
4) ExperimentSpringBoot

KafkaLearning : Is just independent project to understand how Kafka works.
