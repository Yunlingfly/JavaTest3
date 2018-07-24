# 这个是构建Java环境的dockerfile

FROM registry.saas.hand-china.com/hap-cloud/base:latest

WORKDIR /

# 将子项目打包的jar包拷贝到项目根目录
COPY target/test1.jar /test1.jar

# dockerfile执行jar文件
CMD ["java", "-jar", "test1.jar"]