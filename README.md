## 换肤方案：

[Android-skin-support](https://github.com/ximsfei/Android-skin-support)

## 换肤时增加了动画

![](https://github.com/wutongke/AndroidSkinAnimator/blob/master/gif/5.gif)
![](https://github.com/wutongke/AndroidSkinAnimator/blob/master/gif/6.gif)
![](https://github.com/wutongke/AndroidSkinAnimator/blob/master/gif/7.gif)
![](https://github.com/wutongke/AndroidSkinAnimator/blob/master/gif/8.gif)


## 增加了一些View动画

![](https://github.com/wutongke/AndroidSkinAnimator/blob/master/gif/9.gif)
![](https://github.com/wutongke/AndroidSkinAnimator/blob/master/gif/10.gif)

## 全局动画

全局动画和换肤原理相同，直接替换了View组件，原理可以参考[Android-skin-support](https://github.com/ximsfei/Android-skin-support)

![](https://github.com/wutongke/AndroidSkinAnimator/blob/master/gif/11.gif)

### 原理概述

了解过换肤框架的同学知道，利用```setFactory(LayoutInflater inflater, LayoutInflaterFactory factory)```可以在不更新布局文件的情况下替换布局中View为自定义View，并且对开发者透明。利用这个原理我们可以把**原生的View替换为支持动画的View**，如TexView在执行setText、setVisibility等操作时执行动画。

同时提供动画执行等开关，在需要时打开，在不需要时关闭即可。

### 使用说明

* 1.继承```SkinCompatActivity```, 同时支持了换肤功能

* 2.如果Activity需要支持全局动画，覆盖方法：

```
    @Override
    protected boolean needAnimator() {
        return true;
    }
```
如果不需要，则不用理会

* 3.在Activity中设置动画配置：

```
        setAnimatorConfig(new AnimatorConfig
                .Builder()
                .textviewTextAnimationType(ViewAnimatorType.AlphaUpdateAnimator)
                .textviewVisibleAnimationType(ViewAnimatorType.TranslationAlphaHideAnimator)
                .build());
```

在其它地方设置配置：
```
AnimatorManager.setConfig(new AnimatorConfig.Builder()
                        .textviewVisibleAnimationType(ViewAnimatorType.TranslationAlphaHideAnimator)
                        .textviewTextAnimationType(ViewAnimatorType.AlphaUpdateAnimator)
                        .build());
```

* 4.在需要的地方打个关闭动画：

```
AnimatorManager.openAnimator();

AnimatorManager.closeAnimator();
```


## 致谢

* [Android-Skin-Loader](https://github.com/fengjundev/Android-Skin-Loader)

* android com.android.support:appcompat-v7源码

## [License MIT](LICENSE)


欢迎关注公众号**wutongke**，每天推送移动开发前沿技术文章：

![wutongke](http://upload-images.jianshu.io/upload_images/1407686-8f64e33d76075d40.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
