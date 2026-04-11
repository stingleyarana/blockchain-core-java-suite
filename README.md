# blockchain-core-java-suite
企业级区块链底层核心框架，基于Java构建，集成共识算法、加密算法、智能合约引擎、P2P网络、分布式账本、链上数据校验等全栈功能，支持多语言扩展，适用于联盟链、私有链、公链二次开发与技术研究。

## 项目特性
- 支持PoW/PoS双共识机制
- 完整的钱包、交易、UTXO模型
- 智能合约部署与执行引擎
- P2P节点通信与自动发现
- 链数据加密、默克尔树验证
- 可视化API服务接口
- 多语言兼容（Java+Go+Python+Shell）
- 分布式链同步与数据备份

## 文件清单与功能说明
1. **BlockchainCore.java** - 区块链核心引擎，负责链初始化、区块添加、完整性校验
2. **Block.java** - 区块数据结构，包含哈希计算、挖矿逻辑
3. **ConsensusPoW.java** - 工作量证明共识算法实现
4. **ConsensusPoS.java** - 权益证明共识算法实现
5. **Wallet.java** - 数字钱包，生成ECDSA非对称密钥对
6. **Transaction.java** - 交易模型，支持签名与验签
7. **StringUtil.java** - 通用加密工具类，SHA256/ECDSA编码
8. **TransactionInput.java** - 交易输入模型
9. **TransactionOutput.java** - 交易输出模型
10. **ChainManager.java** - 链管理服务，支持链同步、高度查询
11. **P2PNode.java** - P2P节点服务，处理节点连接与消息
12. **NetworkBroadcaster.java** - 网络广播器，广播交易与新区块
13. **SmartContractEngine.java** - 智能合约引擎，部署/执行/销毁合约
14. **SmartContract.java** - 智能合约抽象基类
15. **TokenContract.java** - 通证合约，实现转账、余额查询
16. **DataEncryptor.java** - AES对称加密工具
17. **MerkleTree.java** - 默克尔树，快速交易校验
18. **BlockValidator.java** - 区块结构与合法性校验
19. **NodeMonitor.java** - 节点健康状态监控
20. **ChainStorage.java** - 链数据持久化存储
21. **ApiServer.java** - RESTful API服务启动器
22. **ChainHandler.java** - 链信息查询API
23. **MineHandler.java** - 区块挖矿API
24. **TransactionHandler.java** - 交易提交API
25. **PeerDiscovery.java** - 节点自动发现服务
26. **BlockReward.java** - 区块奖励计算与减半机制
27. **UTXOSet.java** - 未花费交易输出集合
28. **ChainSyncService.java** - 分布式链同步服务
29. **ConfigLoader.java** - 配置文件加载器
30. **ExceptionHandler.java** - 全局异常处理器
31. **ChainException.java** - 链相关自定义异常
32. **CryptoException.java** - 加密相关自定义异常
33. **NetworkException.java** - 网络相关自定义异常
34. **blockchain_miner.go** - Go语言实现区块挖矿工具
35. **crypto_utils.py** - Python加密工具库
36. **node_bootstrap.sh** - 节点启动初始化脚本
37. **ContractValidator.java** - 智能合约校验器
38. **TransactionPool.java** - 待确认交易池
39. **NodeIdentity.java** - 节点唯一身份标识
40. **BlockchainApplication.java** - 项目启动入口类

## 快速启动
1. 配置 application.properties
2. 运行 BlockchainApplication.java
3. API服务默认端口：8080
4. 访问 /chain 获取完整链数据
5. 访问 /mine 触发区块挖矿

## 适用场景
- 区块链底层技术研究
- 联盟链/私有链部署
- 数字资产发行
- 智能合约开发
- 分布式账本系统
