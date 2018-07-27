/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.16 : Database - oauth2_jdbc
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`oauth2_jdbc` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `oauth2_jdbc`;

/*Table structure for table `authorities` */

DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `authorities` */

insert  into `authorities`(`username`,`authority`) values ('admin','管理员');

/*Table structure for table `group_authorities` */

DROP TABLE IF EXISTS `group_authorities`;

CREATE TABLE `group_authorities` (
  `group_id` bigint(20) NOT NULL,
  `authority` varchar(50) NOT NULL,
  KEY `fk_group_authorities_group` (`group_id`),
  CONSTRAINT `fk_group_authorities_group` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `group_authorities` */

/*Table structure for table `group_members` */

DROP TABLE IF EXISTS `group_members`;

CREATE TABLE `group_members` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `group_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_group_members_group` (`group_id`),
  CONSTRAINT `fk_group_members_group` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `group_members` */

/*Table structure for table `groups` */

DROP TABLE IF EXISTS `groups`;

CREATE TABLE `groups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `groups` */

/*Table structure for table `oauth_access_token` */

DROP TABLE IF EXISTS `oauth_access_token`;

CREATE TABLE `oauth_access_token` (
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据的创建时间,精确到秒,由数据库在插入数据时取当前系统时间自动生成(扩展字段)',
  `token_id` varchar(255) DEFAULT NULL COMMENT '该字段的值是将access_token的值通过MD5加密后存储的.',
  `token` blob COMMENT '存储将OAuth2AccessToken.java对象序列化后的二进制数据, 是真实的AccessToken的数据值.',
  `authentication_id` varchar(255) DEFAULT NULL COMMENT '该字段具有唯一性, 其值是根据当前的username(如果有),client_id与scope通过MD5加密生成的. 具体实现请参考DefaultAuthenticationKeyGenerator.java类.',
  `user_name` varchar(255) DEFAULT NULL COMMENT '登录时的用户名, 若客户端没有用户名(如grant_type="client_credentials"),则该值等于client_id',
  `client_id` varchar(255) DEFAULT NULL,
  `authentication` blob COMMENT '存储将OAuth2Authentication.java对象序列化后的二进制数据.',
  `refresh_token` varchar(255) DEFAULT NULL COMMENT '该字段的值是将refresh_token的值通过MD5加密后存储的.',
  KEY `token_id_index` (`token_id`),
  KEY `authentication_id_index` (`authentication_id`),
  KEY `user_name_index` (`user_name`),
  KEY `client_id_index` (`client_id`),
  KEY `refresh_token_index` (`refresh_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在项目中,主要操作oauth_access_token表的对象是JdbcTokenStore.java. 更多的细节请参考该类.';

/*Data for the table `oauth_access_token` */

insert  into `oauth_access_token`(`create_time`,`token_id`,`token`,`authentication_id`,`user_name`,`client_id`,`authentication`,`refresh_token`) values ('2018-07-27 18:57:44','78e5ef525e66eaf57cce51947ef12efe','��\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken��6$��\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6�Z���\0\0xpsr\0java.util.Datehj�KYt\0\0xpw\0\0d����xpsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0��^�\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet�l�Z��*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0allxt\0bearert\0$a70eac73-f1dc-45ff-9d94-1165ae2a8ead','59ccfc74cfe9312dfedec4fc39136e1c','admin','web_1','��\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1��\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0��^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	管理员xq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0web_1sr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0	client_idt\0web_1t\0scopet\0allt\0usernamet\0adminxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet�l�Z��*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0q\0~\0!xsq\0~\0\'w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0readsq\0~\0\rt\0writexsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0\'w\0\0\0?@\0\0\0\0\0\0xsq\0~\0\'w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\04sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0 q\0~\0!t\0\rclient_secrett\0\nweb_secretq\0~\0q\0~\0q\0~\0\"q\0~\0#x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0$sr\0java.util.TreeSetݘP���[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0admin',NULL);

/*Table structure for table `oauth_approvals` */

DROP TABLE IF EXISTS `oauth_approvals`;

CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_approvals` */

/*Table structure for table `oauth_client_details` */

DROP TABLE IF EXISTS `oauth_client_details`;

CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL COMMENT '用于唯一标识每一个客户端(client); 在注册时必须填写(也可由服务端自动生成).',
  `resource_ids` varchar(255) DEFAULT NULL COMMENT '客户端所能访问的资源id集合,多个资源时用逗号(,)分隔,如: "unity-resource,mobile-resource".',
  `client_secret` varchar(255) DEFAULT NULL COMMENT '用于指定客户端(client)的访问密匙; 在注册时必须填写(也可由服务端自动生成).',
  `scope` varchar(255) DEFAULT NULL COMMENT '指定客户端申请的权限范围,可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔,如: "read,write".',
  `authorized_grant_types` varchar(255) DEFAULT NULL COMMENT '指定客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials, 若支持多个grant_type用逗号(,)分隔,如: "authorization_code,password".',
  `web_server_redirect_uri` varchar(255) DEFAULT NULL COMMENT '客户端的重定向URI,可为空',
  `authorities` varchar(255) DEFAULT NULL COMMENT '指定客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔, 如: "ROLE_UNITY,ROLE_USER".',
  `access_token_validity` int(11) DEFAULT NULL COMMENT '设定客户端的access_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时).',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '设定客户端的refresh_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天).',
  `additional_information` text COMMENT '这是一个预留的字段,在Oauth的流程中没有实际的使用,可选',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据的创建时间,精确到秒,由数据库在插入数据时取当前系统时间自动生成(扩展字段)',
  `archived` tinyint(1) DEFAULT '0' COMMENT '用于标识客户端是否已存档(即实现逻辑删除),默认值为''0''(即未存档).',
  `trusted` tinyint(1) DEFAULT '0' COMMENT '设置客户端是否为受信任的,默认为''0''(即不受信任的,1为受信任的)',
  `autoapprove` varchar(255) DEFAULT 'false' COMMENT '置用户是否自动Approval操作, 默认值为 ''false'', 可选值包括 ''true'',''false'', ''read'',''write''.',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在项目中,主要操作oauth_client_details表的类是JdbcClientDetailsService.java, 更多的细节请参考该类. \r\n也可以根据实际的需要,去扩展或修改该类的实现.';

/*Data for the table `oauth_client_details` */

insert  into `oauth_client_details`(`client_id`,`resource_ids`,`client_secret`,`scope`,`authorized_grant_types`,`web_server_redirect_uri`,`authorities`,`access_token_validity`,`refresh_token_validity`,`additional_information`,`create_time`,`archived`,`trusted`,`autoapprove`) values ('web','','web_secret','','client_credentials','','read,write',360000,360000,'{}','2018-07-27 18:56:47',0,0,''),('web_1','','web_secret','','password','','read,write',360000,360000,'{}','2018-07-27 18:56:47',0,0,'');

/*Table structure for table `oauth_client_token` */

DROP TABLE IF EXISTS `oauth_client_token`;

CREATE TABLE `oauth_client_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_client_token` */

/*Table structure for table `oauth_code` */

DROP TABLE IF EXISTS `oauth_code`;

CREATE TABLE `oauth_code` (
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据的创建时间,精确到秒,由数据库在插入数据时取当前系统时间自动生成(扩展字段)',
  `code` varchar(255) DEFAULT NULL COMMENT '存储服务端系统生成的code的值(未加密).',
  `authentication` blob COMMENT '存储将AuthorizationRequestHolder.java对象序列化后的二进制数据.',
  KEY `code_index` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在项目中,主要操作oauth_code表的对象是JdbcAuthorizationCodeServices.java. 更多的细节请参考该类. \r\n只有当grant_type为"authorization_code"时,该表中才会有数据产生; 其他的grant_type没有使用该表.';

/*Data for the table `oauth_code` */

/*Table structure for table `oauth_refresh_token` */

DROP TABLE IF EXISTS `oauth_refresh_token`;

CREATE TABLE `oauth_refresh_token` (
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据的创建时间,精确到秒,由数据库在插入数据时取当前系统时间自动生成(扩展字段)',
  `token_id` varchar(255) DEFAULT NULL COMMENT '该字段的值是将refresh_token的值通过MD5加密后存储的.',
  `token` blob COMMENT '存储将OAuth2RefreshToken.java对象序列化后的二进制数据.',
  `authentication` blob COMMENT '存储将OAuth2Authentication.java对象序列化后的二进制数据.',
  KEY `token_id_index` (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在项目中,主要操作oauth_refresh_token表的对象是JdbcTokenStore.java. (与操作oauth_access_token表的对象一样);更多的细节请参考该类. \r\n如果客户端的grant_type不支持refresh_token,则不会使用该表.';

/*Data for the table `oauth_refresh_token` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`username`,`password`,`enabled`) values ('admin','admin',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
