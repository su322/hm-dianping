## 记录
1.添加了后端强制所有响应使用utf-8\
2.如在macbook上运行nginx，把nginx目录下的nginx.conf替换为项目中的nginx.conf，并修改内容：（1）顶部加上user yourname staff;（2）location / {}块中的root改为实际地址 /Users/yourname/xxx/xxx/xxx/nginx-1.18.0/html/hmdp; 然后sudo nginx -s reload就可以在8080端口看到前端了\
3.
