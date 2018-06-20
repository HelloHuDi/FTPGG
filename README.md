<p align="center">
	<img width="72" height="72" src="art/ic_launcher-web.png"/>
</p>
<h3 align="center">FTPGG</h3>
<p align="center">
<a href="" target="_blank"><img src="https://img.shields.io/badge/release-v1.0-blue.svg"></img></a>
</p>


### **本工程目的**：

#### 封装一个Android下的FTP交互工具

### **本工程目标**：

#### 1. 基于[swiftp][0]熟悉ftp

#### 2. 基于[ftp4j][1]实现双向文件传输

#### **本工程源码来源于[swiftp][0]，[ftp4j][1]，并会长期同步原作源码，部分内容可能会基于[GPL](LICENSE)协议稍作修改**

#### **关注本工程的盆友可转至原工程查看源码，感谢开源!**

[0]: https://github.com/ppareit/swiftp
[1]: http://www.sauronsoftware.it/projects/ftp4j/

#### dependencies :

```
dependencies {
    //...
    implementation 'com.hd:ftpgg:1.0'
}
```

#### code :

##### about client

```
FcInfo fcInfo = new FcInfo.Builder()//
                   .setLoginUserName("ftp")//
                   .setLoginPassword("ftp")//
                   .setPort(3535)//
                   .build();
                   
//start-up client
FTPControl.startClient(fcInfo);

//stop client
FTPControl.stopClient(fcInfo);
```

##### about socket

```
FsInfo fsInfo = new FsInfo.Builder()//
                    .setAccountUserName("ftp")//
                    .setAccountPassword("ftp")//
                    .setAllowAnonymous(false)//
                    .setTakeFullWakeLock(true)//
                    .setChrootDirPath("")//
                    .setPortNumber(3535)//
                    .build();

//start-up socket
FTPControl.startSocket(fsInfo);

//stop socket
FTPControl.stopSocket(fsInfo);
```
