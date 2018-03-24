# Minimal framework yet

## Usage

#### Tasks

Launch solution for development

```bash
sbt ~launch
```

Package bootable jar

```bash
sbt assembly
```

Package bootable jar and run it

```bash
sbt runJar
```

## Dependencies

#### JDK
 
```bash
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
```

**java 8**

```bash
sudo apt-get install oracle-java8-installer
```

**or java 9**

```bash
sudo apt-get install oracle-java9-installer
```

#### SBT
 
```bash
echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
sudo apt-get update
sudo apt-get install sbt
```

#### NODE JS 
 
For testing, run in project dir
 
```bash
curl -sL https://deb.nodesource.com/setup_8.x -o nodesource_setup.sh
npm install jsdom
```
 
