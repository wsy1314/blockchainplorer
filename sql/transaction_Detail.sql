/*
SQLyog Ultimate v8.32 
MySQL - 5.5.27 : Database - 1608j
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`1608j` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `1608j`;

/*Table structure for table `repictmaterial` */

DROP TABLE IF EXISTS `repictmaterial`;

CREATE TABLE transaction_Detail (
  tx_detail_id bigint(28)not null auto_increment,
  txid char(64) NOT NULL,
  address varchar(50) ,
  type tinyint,
  amount varchar(10),
  PRIMARY KEY (tx_detail_id),
  index idx_txid(txid),
  index idx_address(address)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `repictmaterial` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
