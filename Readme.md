

Encrypter
=========
一个加密工具



加密算法
====
加密文件格式如下：\
![加密文件的格式](introduce/file_format.png)

* magic为固定的魔数，用于分辨文件格式，内容为"zzprivat"
* length为原文长度，以字节计算。
* salt为每次加密都要重新生成的随机序列
* (magic+magic)^md5(password)用于解密时分辨密码是否正确。
* 校验码用于保证数据的完整性

两个加密区分别使用流加密算法加密，密钥是(password+salt)

流加密算法
=====

随机序列生成算法
========

