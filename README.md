# MyFirstApp 学习笔记

这个仓库用于记录我学习 Android 原生开发（Kotlin + Jetpack Compose）的过程。  
学习目标是通过“可运行的小练习”逐步建立能力，而不是只看概念。

## 一、项目目标（学习计划）

### 已完成

1. 环境与运行闭环
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
- 输入内容实时反映到 `Text`

4. Git 基础工程化
- 本地初始化 Git 并完成首次 commit
- 创建 GitHub 仓库并 push
- 配置 `.gitignore` 规则

### 下一步计划

1. `LazyColumn` 列表练习  
2. 导航（列表页 -> 详情页）  
3. 网络请求（加载/成功/失败三态）  
4. ViewModel 状态托管  
5. Room 本地存储

---

## 二、正文（开发笔记）

### 1) Compose 状态交互基本公式

核心记忆：

`remember + mutableState = 能记住、会触发重绘的状态`

示例（Int）：

```kotlin
var count by remember { mutableIntStateOf(0) }
```

这行代码做了三件事：

1. `mutableIntStateOf(0)`  
将 `count` 变成可观察状态，值变化时 Compose 知道要重绘 UI。

2. `remember { ... }`  
在同一个 Composable 的重组过程中“记住”状态，不会每次重绘都回到初始值。

3. `by`（属性委托）  
可以像普通变量那样写 `count++`，不用写 `count.value++`。

为什么 `var count = 0` 不行：

1. 它不是可观察状态，变化后不会自动刷新 UI。  
2. 重组时可能再次初始化为 0，导致“值回弹”。

---

### 2) Scaffold 与 innerPadding

`Scaffold` 是页面骨架容器（topBar / bottomBar / FAB / content）。  
`innerPadding` 是内容区的安全内边距，避免内容被顶栏/底栏/系统栏遮挡。

实践规则：

```kotlin
Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
    Content(modifier = Modifier.padding(innerPadding))
}
```

---

### 3) Row / OutlinedTextField / Button 交互理解

1. `Row` 是横向布局容器，会把子组件从左到右排。  
把 `OutlinedTextField` 和 `Button` 放在同一行是标准做法。

2. `OutlinedTextField` 的 `label = { Text("新增待办") }` 是插槽 lambda（Composable lambda），用于传入标签 UI。

3. `onValueChange` 是回调函数。输入框内容变化时会触发回调，把新值写回状态：

```kotlin
onValueChange = { inputText = it }
```

4. `inputText.trim()` 是字符串清理（去掉首尾空格），不是“取值”。

5. 按钮垂直居中的正确做法：
- 在 `Row` 上加 `verticalAlignment = Alignment.CenterVertically`，或
- 给 `Button` 加 `Modifier.align(Alignment.CenterVertically)`

不要把 `align(...)` 写在 `Button` 里的 `Text` 上（它不是 Row 的直接子项）。

---

### 4) todos 列表状态 / Column vs LazyColumn / items

1. `todos` 使用 `mutableStateListOf(...)` 时是 `SnapshotStateList`。  
它能像 List 一样用，但属于可观察列表；`add/remove` 会触发 UI 自动刷新。

2. `Column` 与 `LazyColumn`：
- `Column`：一次性布局所有子项，适合少量固定内容。
- `LazyColumn`：懒加载列表，只渲染可见项，适合数据列表，性能更好。

3. `items(...)` 是 `LazyColumn` 的构建函数（`LazyListScope` 扩展），用于遍历集合并为每个元素生成 UI。

```kotlin
LazyColumn {
    items(todos) { item ->
        Text(item)
    }
}
```

---

### 5) Modifier 学习总结

我已掌握 `Modifier` 的基本用法：  
通过链式调用控制布局、间距、大小和交互，且顺序会影响最终效果。

---

## 三、附录（Android Studio 操作技巧）

### 常用视图

1. `Android` 视图：日常开发更友好。  
2. `Project` 视图：看真实目录结构，便于找 `build/outputs/apk`。

### 日志与排查

1. 打开 Logcat：`View > Tool Windows > Logcat`  
2. 过滤当前应用：`package:mine`

### 查找与跳转

1. 当前文件查找：`Ctrl + F`  
2. 全项目查找：`Ctrl + Shift + F`  
3. 跳定义：`Ctrl + B` / `Ctrl + Click`  
4. 查引用：`Alt + F7`

### 自动导包

1. 报红符号按 `Alt + Enter` 自动导包。  
2. 优先使用 IDE 内置能力，不急于装第三方插件。

### 构建与 APK

1. 生成调试 APK：  
`Build > Generate App Bundles or APKs > Generate APKs`

2. 调试 APK 路径：  
`app/build/outputs/apk/debug/app-debug.apk`

### Git 基础流程

1. `Commit`：提交到本地仓库。  
2. `Push`：同步到 GitHub 远端。  
3. 推荐小步提交，信息清晰（`feat/fix/docs/chore`）。

---

## 四、迭代记录

- v0.1：完成环境搭建、首个项目运行、状态交互基础练习、首次 GitHub 同步。  
- v0.2：补充 Row/输入框回调/列表懒加载/Modifier 学习笔记。  

