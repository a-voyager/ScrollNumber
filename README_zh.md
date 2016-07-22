# 滚动数字控件

[中文](https://github.com/a-voyager/ScrollNumber/blob/master/README_zh.md) | [English](https://github.com/a-voyager/ScrollNumber/blob/master/README.md)

一个 **简单**、**优雅**、**易用** 的滚动数字控件！

> 别忘记给个Star支持一下哦 :）

## 特点
 - 只需要调用 `setNumber()` 即可，非常简单.
 - 可以**动态地**修改数字的大小、颜色、范围、字体等等...

![image](https://github.com/a-voyager/ScrollNumber/raw/master/imgs/01.gif)

## 依赖
可以选择两种方式:

 - 克隆本项目，然后在你的IDE中依赖此项目即可
 - 只需要在build.gradle中添加一下代码即可(可能暂时无法使用):

 ```groovy
 compile 'top.wuhaojie:scrollnumber:1.0.0'
 ```

## 用法
 -  在布局文件中添加:

```xml
<top.wuhaojie.library.MultiScrollNumber
    android:id="@+id/scroll_number"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```
 - 在Java代码中调用 `setNumber()` 即可:

```java
MultiScrollNumber scrollNumber = (MultiScrollNumber) findViewById(R.id.scroll_number);
scrollNumber.setNumber(2048);
```

## 自定义
 - 颜色
 调用 `setTextColors(@ColorRes int[] textColors)` 方法， 参数为数组， 存放的是**从高位到低位**的颜色.

```java
scrollNumber.setTextColors(new int[]{R.color.blue01, R.color.red01,
                R.color.green01, R.color.purple01});
```

![image](https://github.com/a-voyager/ScrollNumber/raw/master/imgs/02.gif)

 - 大小
 只需要在想要改变字体大小的时候直接调用 `setTextSize(int textSize)` 即可， 注意参数的单位是 `sp` .

```java
scrollNumber.setTextSize(64);
```

![image](https://github.com/a-voyager/ScrollNumber/raw/master/imgs/03.gif)

 - 范围
 调用 `setNumber(int from, int to)` 来代替 `setNumber(int val)` 可以明确指出起始范围.

```java
scrollNumber.setNumber(64, 2048);
```

![image](https://github.com/a-voyager/ScrollNumber/raw/master/imgs/04.gif)

 - 插值器
 可以调用 `setInterpolator(Interpolator interpolator)` 来使用其它的插值器.

```java
scrollNumber.setInterpolator(new DecelerateInterpolator());
```

 - 字体
 通过调用 `setTextFont(fileName)` 来改变字体, 记得在这之前把字体文件拷贝到 `assets/` 目录.

```java
scrollNumber.setTextFont("myfont.ttf");
```

![image](https://github.com/a-voyager/ScrollNumber/raw/master/imgs/05.png)

## Xml属性
| 含义       | 属性     |
| ------------- |:-------------:|
| 开始数字 |primary_number |
| 结束数字   | target_number |
| 字体大小   | number_size   |


## 更新日志

 - 添加字体修改支持 2016年7月22日

## License
    The MIT License (MIT)

    Copyright (c) 2015 WuHaojie

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.