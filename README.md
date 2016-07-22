# Scroll Number Widget

[中文](https://github.com/a-voyager/ScrollNumber/blob/master/README_zh.md) | [English](https://github.com/a-voyager/ScrollNumber/blob/master/README.md)

A **Simple**、**Graceful**、**Easy-to-Use** Scroll Number Widget！

> Don't forget give me a star :）

## Feature
 - Just need to call `setNumber()` could be performed.
 - You can **dynamically** customize number's colors、size、range、font...

![image](https://github.com/a-voyager/ScrollNumber/raw/master/imgs/01.gif)

## Dependency
There are two ways:

 - clone this project, and use as dependency
 - just add following code to you build.gradle:

 ```groovy
 compile 'top.wuhaojie:scrollnumber:1.0.0'
 ```

## Usage
 -  Add this to your layout xml file:

```xml
<top.wuhaojie.library.MultiScrollNumber
    android:id="@+id/scroll_number"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```
 - Call `setNumber()` in your java code:

```java
MultiScrollNumber scrollNumber = (MultiScrollNumber) findViewById(R.id.scroll_number);
scrollNumber.setNumber(2048);
```

## Customize
 - Color
 Call `setTextColors(@ColorRes int[] textColors)` with a paramiter, a array stores colors **from High bit to Low bit**.

```java
scrollNumber.setTextColors(new int[]{R.color.blue01, R.color.red01,
                R.color.green01, R.color.purple01});
```

![image](https://github.com/a-voyager/ScrollNumber/raw/master/imgs/02.gif)

 - Size
 Just call `setTextSize(int textSize)` with the size you want to change to, unit is `sp` .

```java
scrollNumber.setTextSize(64);
```

![image](https://github.com/a-voyager/ScrollNumber/raw/master/imgs/03.gif)

 - Range
 Call `setNumber(int from, int to)` instead of `setNumber(int val)` to specify a range.

```java
scrollNumber.setNumber(64, 2048);
```

![image](https://github.com/a-voyager/ScrollNumber/raw/master/imgs/04.gif)

 - Interpolator
 You could change interpolator with call `setInterpolator(Interpolator interpolator)`.

```java
scrollNumber.setInterpolator(new DecelerateInterpolator());
```

 - Font
 Change text font by call `setTextFont(fileName)`, before this you should copy the font file to `assets/` directory.

```java
scrollNumber.setTextFont("myfont.ttf");
```

![image](https://github.com/a-voyager/ScrollNumber/raw/master/imgs/05.png)

## Xml
| Meaning       | Attribute     |
| ------------- |:-------------:|
| from a number |primary_number |
| to a number   | target_number |
| number size   | number_size   |


## Update Log

 - add font change option on 22/7/2016.

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