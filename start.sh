#sudo apt-get update



#######functions#######
install_java(){
	echo installing java
	sudo apt install openjdk-8-jre-headless
	sudo apt install apt-get install default-jdk
}
install_mysql(){
	echo installing mysql
	sudo apt-get install mysql-server
	sudo mysql_secure_installation
	sudo mysql -u root -p
    uninstall plugin validate_password;
    ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '12345678';
}
install_maven(){
	sudo apt-get install maven
}



if type java; then
    _java=java
    echo $_java
else
    install_java
    _java=java
    echo $_java
fi


sq="$(which mysqld)"

if [ -z "$sq" ] ; then
    install_mysql
fi

m="$(which mvn)"
if  [ -z "$m" ] ;  then
	install_mavens
fi


mysql -u root -p12345678 < "lalamove_order_order_tb.sql"
sudo apt-get install docker.io
sudo mvn install dockerfile:build
sudo docker run -p 8080:8080 -t springio/org.lalamove
exit 0

