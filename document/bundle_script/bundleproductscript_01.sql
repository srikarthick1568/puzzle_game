---bundle product header table---

CREATE TABLE `bundleproductheader` (
  `CmpCode` varchar(10) NOT NULL,
  `DistrCode` varchar(50) NOT NULL,
  `DistrBrCode` varchar(30) NOT NULL,
  `ProductCode` varchar(50) NOT NULL,
  `ProductName` varchar(400) DEFAULT NULL,
  `ShortName` varchar(400) DEFAULT NULL,
  `ParentCode` varchar(100) DEFAULT NULL,
  `NetWgt` decimal(9,3) DEFAULT NULL,
  `WeightType` char(5) DEFAULT NULL,
  `ShelfLife` int DEFAULT NULL,
  `PtrPerPc` varchar(15) DEFAULT NULL,
  `Volume` varchar(15) DEFAULT NULL,
  `StandardNR` varchar(15) DEFAULT NULL,
  `CaseLot` varchar(15) DEFAULT NULL,
  `IsExempted` tinyint(1) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  `ModUserCode` varchar(50) DEFAULT NULL,
  `ModDt` datetime DEFAULT NULL,
  `ApprovalFlag` varchar(1) DEFAULT 'P',
  `remark` varchar(30) DEFAULT NULL,
  `createdDt` datetime DEFAULT NULL,
  PRIMARY KEY (`CmpCode`,`DistrCode`,`DistrBrCode`,`ProductCode`)
);


---bundle Product Details table---

CREATE TABLE BundleProductDetails (
CREATE TABLE `bundleproductdetails` (
  `CmpCode` varchar(10) NOT NULL,
  `DistrCode` varchar(50) NOT NULL,
  `DistrBrCode` varchar(30) NOT NULL,
  `ParentCode` varchar(100) NOT NULL,
  `Qty` int NOT NULL,
  `ProductCode` varchar(50) NOT NULL DEFAULT '',
  `ProductName` varchar(400) DEFAULT NULL,
  `ShortName` varchar(400) DEFAULT NULL,
  `Franchise` varchar(100) DEFAULT NULL,
  `Brand` varchar(100) DEFAULT NULL,
  `ProductCategory` varchar(100) DEFAULT NULL,
  `Variant` varchar(150) DEFAULT NULL,
  `MotherSKU` varchar(150) DEFAULT NULL,
  `ProductType` varchar(50) DEFAULT NULL,
  `VmrType` varchar(50) DEFAULT NULL,
  `EanCode` varchar(15) DEFAULT NULL,
  `NetWgt` decimal(9,3) DEFAULT NULL,
  `WeightType` char(5) DEFAULT NULL,
  `ShelfLife` int DEFAULT NULL,
  `PtrPerPc` varchar(15) DEFAULT NULL,
  `Volume` varchar(15) DEFAULT NULL,
  `StandardNR` varchar(15) DEFAULT NULL,
  `CaseLot` varchar(15) DEFAULT NULL,
  `IsExempted` tinyint(1) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  `InternationalUOM` varchar(15) DEFAULT NULL,
  `stockType` varchar(30) DEFAULT NULL,
  `uom` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CmpCode`,`DistrCode`,`DistrBrCode`,`ParentCode`,`ProductCode`),
  CONSTRAINT `FK_BundleProductDetails_BundleProductHeader` FOREIGN KEY (`CmpCode`, `DistrCode`, `DistrBrCode`, `ParentCode`) REFERENCES `bundleproductheader` (`CmpCode`, `DistrCode`, `DistrBrCode`, `ProductCode`) ON DELETE RESTRICT ON UPDATE RESTRICT
);



---bundle product log table---


CREATE TABLE `bundlecreationlog` (
  `CmpCode` varchar(10) NOT NULL,
  `DistrCode` varchar(50) NOT NULL,
  `WorkAssignmentID` varchar(50) DEFAULT NULL,
  `ApprovedProductCode` varchar(50) DEFAULT NULL,
  `ApprovedBy` varchar(50) DEFAULT NULL,
  `ApprovedDate` datetime DEFAULT NULL,
  `Payload` json DEFAULT NULL,
  `ModUserCode` varchar(50) DEFAULT NULL,
  `Moddt` datetime DEFAULT NULL,
  `currentstatus` varchar(1) DEFAULT 'P',
  `reason` varchar(30) DEFAULT NULL
) ;


--BUNDLE PRODUCT DISTRWISE HEADER--

CREATE TABLE `bundleproductdistrheader` (
  `CmpCode` varchar(10) NOT NULL,
  `DistrCode` varchar(50) NOT NULL,
  `DistrBrCode` varchar(30) NOT NULL,
  `ProductCode` varchar(50) NOT NULL,
  `ProductName` varchar(400) DEFAULT NULL,
  `ShortName` varchar(400) DEFAULT NULL,
  `ParentCode` varchar(100) DEFAULT NULL,
  `NetWgt` decimal(9,3) DEFAULT NULL,
  `WeightType` char(5) DEFAULT NULL,
  `ShelfLife` int DEFAULT NULL,
  `PtrPerPc` varchar(15) DEFAULT NULL,
  `Volume` varchar(15) DEFAULT NULL,
  `StandardNR` varchar(15) DEFAULT NULL,
  `CaseLot` varchar(15) DEFAULT NULL,
  `IsExempted` tinyint(1) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  `ModUserCode` varchar(50) DEFAULT NULL,
  `ModDt` datetime DEFAULT NULL,
  `remark` varchar(30) DEFAULT NULL,
  `createdDt` datetime DEFAULT NULL,
  PRIMARY KEY (`CmpCode`,`DistrCode`,`DistrBrCode`,`ProductCode`)
) ;



---BUNDLE PRODUCT DISTRWISE DETAILS---

CREATE TABLE `bundleproductdistrdetails` (
  `CmpCode` varchar(10) NOT NULL,
  `DistrCode` varchar(50) NOT NULL,
  `DistrBrCode` varchar(30) NOT NULL,
  `ParentCode` varchar(100) NOT NULL,
  `Qty` int NOT NULL,
  `ProductCode` varchar(50) NOT NULL DEFAULT '',
  `ProductName` varchar(400) DEFAULT NULL,
  `ShortName` varchar(400) DEFAULT NULL,
  `Franchise` varchar(100) DEFAULT NULL,
  `Brand` varchar(100) DEFAULT NULL,
  `ProductCategory` varchar(100) DEFAULT NULL,
  `Variant` varchar(150) DEFAULT NULL,
  `MotherSKU` varchar(150) DEFAULT NULL,
  `ProductType` varchar(50) DEFAULT NULL,
  `VmrType` varchar(50) DEFAULT NULL,
  `EanCode` varchar(15) DEFAULT NULL,
  `NetWgt` decimal(9,3) DEFAULT NULL,
  `WeightType` char(5) DEFAULT NULL,
  `ShelfLife` int DEFAULT NULL,
  `PtrPerPc` varchar(15) DEFAULT NULL,
  `Volume` varchar(15) DEFAULT NULL,
  `StandardNR` varchar(15) DEFAULT NULL,
  `CaseLot` varchar(15) DEFAULT NULL,
  `IsExempted` tinyint(1) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  `InternationalUOM` varchar(15) DEFAULT NULL,
  `stockType` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`CmpCode`,`DistrCode`,`DistrBrCode`,`ParentCode`,`ProductCode`),
  CONSTRAINT `FK_BundleProductDistrDetails_BundleProductDistrHeader` FOREIGN KEY (`CmpCode`, `DistrCode`, `DistrBrCode`, `ParentCode`) REFERENCES `bundleproductdistrheader` (`CmpCode`, `DistrCode`, `DistrBrCode`, `ProductCode`) ON DELETE RESTRICT ON UPDATE RESTRICT
);
