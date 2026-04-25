# MyFirstApp 学习笔记

这是一个用于记录我学习 Android 原生开发（Kotlin + Jetpack Compose）的实践仓库。  
目标不是一次性写完大项目，而是通过可运行的小练习，持续积累开发能力与工程习惯。

## 一、项目目标（学习计划）

### 已完成

1. 环境搭建与运行闭环
- 安装 Android Studio（SDK 放在 D 盘）
- 创建首个 Compose 项目
- 创建模拟器并成功运行
- 完成首次 APK 构建

2. 状态交互练习 1：计数器
- 使用 `remember + mutableIntStateOf` 管理计数状态
- 点击按钮实现 `+1 / -1 / 重置`

3. 状态交互练习 2：输入框实时更新 UI
- 使用 `OutlinedTextField`
- 使用 `mutableStateOf("")` 管理字符串状态
- 输入内容实时反映到 `Text` 显示

### 进行中

4. Git 工程化习惯
- 本地初始化 Git 并完成首个 commit
- 创建 GitHub 仓库并 push 到远端
- 建立 `.gitignore` 规则，避免提交构建缓存和敏感文件

### 下一步计划

1. `LazyColumn` 列表练习  
2. 导航（列表页 -> 详情页）  
3. 网络请求（加载/成功/失败三态）  
4. ViewModel 状态托管  
5. Room 本地存储

---

## 二、正文（开发笔记）

### 1. Compose 状态交互基本公式

核心记忆：

`remember + mutableState = 能记住、会触发重绘的状态`

示例（Int）：

```kotlin
var count by remember { mutableIntStateOf(0) }
```

这行代码做了 3 件事：

1. `mutableIntStateOf(0)`  
把 `count` 变成可观察状态，值变化时 UI 会重绘。

2. `remember { ... }`  
在同一个 Composable 的重组过程中记住状态，不会每次重绘都回到初始值。

3. `by`（属性委托）  
可直接写 `count++`，不需要 `count.value++`。

为什么 `var count = 0` 不行：

1. 不是可观察状态，变化后不触发 UI 自动更新。  
2. 重组时可能重新初始化，出现“值回弹”。

### 2. `Scaffold` 与 `innerPadding`

`Scaffold` 是页面骨架容器（topBar / bottomBar / FAB / content）。  
`innerPadding` 是内容区的安全内边距，目的是避免内容被顶栏/底栏/系统栏遮挡。

实践规则：

只要用了 `Scaffold`，内容根节点优先加：

```kotlin
Modifier.padding(innerPadding)
```

### 3. 为什么会出现 UI 重叠

曾出现过把 `CounterScreen()` 和 `NameCardScreen()` 同时放在 `Scaffold` content 里，且两者都 `fillMaxSize()`，导致重叠。

解决方法：

1. 一次只放一个整屏 Composable，或  
2. 用父级 `Column/Row` 做布局，不让多个子项都占满全屏。

### 4. `@Preview` 的作用

`@Preview` 不是运行时入口，只用于 IDE 预览。  
真正运行入口是 `MainActivity` 中的 `setContent { ... }`。

---

## 三、附录（Android Studio 操作技巧）

### 1. 常用视图

1. `Android` 视图：日常开发更友好。  
2. `Project` 视图：看真实目录结构，便于找 `build/outputs/apk`。

### 2. 日志与排查

1. 打开 Logcat：`View > Tool Windows > Logcat`  
2. 常用过滤：`package:mine`（只看当前 app）

### 3. 查找与跳转

1. 当前文件查找：`Ctrl + F`  
2. 全项目查找：`Ctrl + Shift + F`  
3. 跳定义：`Ctrl + B` / `Ctrl + Click`  
4. 查引用：`Alt + F7`

### 4. 自动导包

1. 报红符号按 `Alt + Enter` 自动导包。  
2. 建议优先用 IDE 内置功能，不急于安装第三方插件。

### 5. 构建与 APK

1. 生成调试 APK：  
`Build > Generate App Bundles or APKs > Generate APKs`

2. 调试 APK 默认路径：  
`app/build/outputs/apk/debug/app-debug.apk`

### 6. Git 基础流程

1. `Commit`：提交到本地仓库。  
2. `Push`：同步到 GitHub 远端。  
3. 推荐小步提交，提交信息清晰：`feat/fix/chore`。

---

## 四、迭代记录（可持续追加）

- v0.1：完成环境搭建、首个项目运行、状态交互基础练习、首次 GitHub 同步。  

