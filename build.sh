echo "开始打包..."

cd Exam1
echo "第一个题目"
mvn clean
mvn compile
mvn package
cd target
java -jar test1.jar 2 2
cd ..
cd ..
# docker build -t mysql:test ./mysql
# docker build -t jar:test .