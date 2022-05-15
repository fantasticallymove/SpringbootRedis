# Master 分支 將呈現 使用一主一從的配置
使用@RedisHash方式操作寫redis , RedisTemplate 操作讀

請使用以下連結獲取Redis 配置內容
https://www.evernote.com/shard/s682/sh/d36d1660-557a-6340-224c-034cc0e14f9d/57ba8e413191e8f1215f67b12914c32e

相關redis-Cli 可操作指令
https://kknews.cc/zh-tw/code/9py6pel.html

#注意事項 RedisTemplate 使用類似Jpa的方式操作Redis 的話,需要依照Key 的類型 使用不同的Serializer 否則會發生,連上Redis 但是Redis 永遠找不到Key 或是東西的情況。

----------------------------------------------------------------------------------------------------------------------------------------------------------------


# cluster 分支 將呈現範例 3主3從 叢集方式配置
使用 StringSerialize

目前已知問題使用當前官方Lettuce Configuration 會導致 java.lang.IllegalArgumentException: Malformed \uxxxx encoding while mvn install
解決方法:改回Jedis以一般方式連接Redis 不使用Netty底層方法連結
目前沒有找到解決方法,但Lettuce 官方似乎有其他配置方式,待嘗試。 2022/05/15
