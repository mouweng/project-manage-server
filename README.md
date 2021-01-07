# projectManage-server

#### 介绍
项目管理系统后端-实训



#### Git规范

```sh
# 每次添加一个新feature都从远程仓库拉origin/development分支，切换新的分支
git pull origin/development
git checkout -b feature-xxx origin/development
# coding

git add .
git commit -m "xxx"

git pull origin/development
git merge origin/development
# 如果有冲突，解决它，并add、commit

git push origin feature-xxx
# 提merge request，源分支feature-xxx，目标分支development, 然后管理员合并
# 参考https://blog.csdn.net/torpidcat/article/details/105213254
```

