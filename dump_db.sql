/*
 Navicat Premium Data Transfer

 Source Server         : tweket.database.windows.net
 Source Server Type    : SQL Server
 Source Server Version : 12001000
 Source Host           : tweket.database.windows.net:1433
 Source Catalog        : tweket
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 12001000
 File Encoding         : 65001

 Date: 28/11/2018 18:34:57
*/


-- ----------------------------
-- Table structure for Client
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[Client]') AND type IN ('U'))
	DROP TABLE [dbo].[Client]
GO

CREATE TABLE [dbo].[Client] (
  [id] int  IDENTITY(1,1) NOT NULL,
  [name] varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [dni] varchar(12) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [address] varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [phone] varchar(15) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [mail] varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [zone_code] int  NULL
)
GO


-- ----------------------------
-- Records of Client
-- ----------------------------
BEGIN TRANSACTION
GO

SET IDENTITY_INSERT [dbo].[Client] ON
GO

INSERT INTO [dbo].[Client] ([id], [name], [dni], [address], [phone], [mail], [zone_code]) VALUES (N'1', N'Aceitera Gral Deheza', N'20202020', N'Azcuenaga 77', N'1546782243', N'agd@gmail.com', N'1')
GO

INSERT INTO [dbo].[Client] ([id], [name], [dni], [address], [phone], [mail], [zone_code]) VALUES (N'2', N'Cosmeticos el Zuzu', N'13456234', N'Larralde 3321', N'1546782243', N'zuzu@gmail.com', N'7')
GO

INSERT INTO [dbo].[Client] ([id], [name], [dni], [address], [phone], [mail], [zone_code]) VALUES (N'3', N'Tucu Tucu', N'45672342', N'Av Costituyentes 2235', N'1123563436', N'tucutucu@gmail.com', N'1')
GO

INSERT INTO [dbo].[Client] ([id], [name], [dni], [address], [phone], [mail], [zone_code]) VALUES (N'4', N'Ferreteria la cuña', N'10234879', N'Avellaneda 762', N'1123466436', N'ferrelacuna@gmail.com', N'10')
GO

INSERT INTO [dbo].[Client] ([id], [name], [dni], [address], [phone], [mail], [zone_code]) VALUES (N'5', N'RES', N'443356522', N'Conde 3469', N'08004443456', N'res@gmail.com', N'8')
GO

INSERT INTO [dbo].[Client] ([id], [name], [dni], [address], [phone], [mail], [zone_code]) VALUES (N'6', N'Fiambreria el "Roque"', N'1093245', N'Carregal 123', N'45718412', N'roque@gmail.com', N'5')
GO

INSERT INTO [dbo].[Client] ([id], [name], [dni], [address], [phone], [mail], [zone_code]) VALUES (N'7', N'Tuercas Mencho', N'83479234', N'Concepción 18', N'50021234', N'roque@gmail.com', N'9')
GO

INSERT INTO [dbo].[Client] ([id], [name], [dni], [address], [phone], [mail], [zone_code]) VALUES (N'12', N'Tato Bazar', N'2034567892', N'Av Boedo 1264', N'49328876', N'tatobazar@mail.com', N'10')
GO

INSERT INTO [dbo].[Client] ([id], [name], [dni], [address], [phone], [mail], [zone_code]) VALUES (N'14', N'NorTech', N'2900465789', N'Av. Maipu 2498', N'4765-4321', N'nortech@mail.com', N'1')
GO

INSERT INTO [dbo].[Client] ([id], [name], [dni], [address], [phone], [mail], [zone_code]) VALUES (N'16', N'AFIP', N'33693450239', N'Alsina 149', N'4342-9088', N'afip@afip.gob.ar', N'10')
GO

INSERT INTO [dbo].[Client] ([id], [name], [dni], [address], [phone], [mail], [zone_code]) VALUES (N'17', N'Pancheria "El Bajon"', N'209174883321', N'Av. Leandro  Alem 1417', N'4988-7766', N'elbajon@mail.com', N'10')
GO

SET IDENTITY_INSERT [dbo].[Client] OFF
GO

COMMIT
GO


-- ----------------------------
-- Table structure for Product
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[Product]') AND type IN ('U'))
	DROP TABLE [dbo].[Product]
GO

CREATE TABLE [dbo].[Product] (
  [product_code] varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [title] varchar(40) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [description] varchar(40) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [price] float(53)  NULL
)
GO


-- ----------------------------
-- Records of Product
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[Product]  VALUES (N'2035', N'Mochila UA', N'Mochila Under Armour - TechLand', N'1600')
GO

INSERT INTO [dbo].[Product]  VALUES (N'32404', N'Vaso Termico', N'Vaso termico motivos Disney', N'160')
GO

INSERT INTO [dbo].[Product]  VALUES (N'4040', N'Aceite Marolio', N'Aceite primera marca.', N'45')
GO

INSERT INTO [dbo].[Product]  VALUES (N'4578', N'Aceite Pa''Tito', N'Aceite segunda marca Pa''Tito.', N'10')
GO

INSERT INTO [dbo].[Product]  VALUES (N'8762', N'Armario', N'Armario de cocina', N'2000')
GO

INSERT INTO [dbo].[Product]  VALUES (N'994765432', N'Lapiz', N'Lapiz HB1.0', N'15')
GO

INSERT INTO [dbo].[Product]  VALUES (N'9975', N'Televisor 4K', N'Televisor UHD 4K - Samsung', N'40000')
GO

INSERT INTO [dbo].[Product]  VALUES (N'BZ022014', N'Taza Londres', N'Taza ceramica motivo Londres', N'350')
GO

INSERT INTO [dbo].[Product]  VALUES (N'C0023', N'Smartphone', N'Smartphone 5 pulgadas', N'9900')
GO

INSERT INTO [dbo].[Product]  VALUES (N'LE910', N'Lenovo YOGA 910', N'Lebnovo Yoga 910', N'45000')
GO

INSERT INTO [dbo].[Product]  VALUES (N'P01', N'CIF', N'Desinfectante', N'22')
GO

INSERT INTO [dbo].[Product]  VALUES (N'P02', N'Algodon', N'Algodon primera marca - Estrella', N'55')
GO

INSERT INTO [dbo].[Product]  VALUES (N'P03', N'Dentrifico', N'limpiador de dientes', N'55.4000015258789')
GO

INSERT INTO [dbo].[Product]  VALUES (N'P04', N'Lavandina', N'desinfectante', N'55.4000015258789')
GO

INSERT INTO [dbo].[Product]  VALUES (N'P05', N'Perfume de ambiente', N'perfume', N'55.4')
GO

INSERT INTO [dbo].[Product]  VALUES (N'P06', N'Caotrina', N'insecticida', N'55.4')
GO

INSERT INTO [dbo].[Product]  VALUES (N'SK9866332', N'Monitor 20"', N'Monitor 20" LG', N'2300')
GO

INSERT INTO [dbo].[Product]  VALUES (N'TS10001', N'Mouse Genius', N'Mouse inalambrico Genius', N'150')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for ROLE
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[ROLE]') AND type IN ('U'))
	DROP TABLE [dbo].[ROLE]
GO

CREATE TABLE [dbo].[ROLE] (
  [id] int  IDENTITY(1,1) NOT NULL,
  [type] varchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL
)
GO


-- ----------------------------
-- Records of ROLE
-- ----------------------------
BEGIN TRANSACTION
GO

SET IDENTITY_INSERT [dbo].[ROLE] ON
GO

INSERT INTO [dbo].[ROLE] ([id], [type]) VALUES (N'1', N'admin')
GO

INSERT INTO [dbo].[ROLE] ([id], [type]) VALUES (N'5', N'Zona Entrega')
GO

INSERT INTO [dbo].[ROLE] ([id], [type]) VALUES (N'6', N'CallCenter')
GO

INSERT INTO [dbo].[ROLE] ([id], [type]) VALUES (N'7', N'Distribucion')
GO

INSERT INTO [dbo].[ROLE] ([id], [type]) VALUES (N'8', N'Consulta')
GO

INSERT INTO [dbo].[ROLE] ([id], [type]) VALUES (N'9', N'Facturacion')
GO

SET IDENTITY_INSERT [dbo].[ROLE] OFF
GO

COMMIT
GO


-- ----------------------------
-- Table structure for ROLE_USER
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[ROLE_USER]') AND type IN ('U'))
	DROP TABLE [dbo].[ROLE_USER]
GO

CREATE TABLE [dbo].[ROLE_USER] (
  [user_id] int  NOT NULL,
  [role_id] int  NOT NULL
)
GO


-- ----------------------------
-- Records of ROLE_USER
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'1', N'1')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'2', N'2')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'3', N'3')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'4', N'4')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'6', N'6')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'7', N'7')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'5', N'5')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'11', N'1')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'9', N'9')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'1', N'5')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'1', N'6')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'1', N'7')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'4', N'9')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'8', N'8')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'11', N'8')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'12', N'7')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'11', N'5')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'11', N'6')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'11', N'7')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'11', N'9')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'13', N'6')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'12', N'1')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'14', N'9')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'15', N'7')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'17', N'5')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'1', N'8')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'1', N'9')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'3', N'5')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'3', N'6')
GO

INSERT INTO [dbo].[ROLE_USER]  VALUES (N'3', N'1')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for STATUS
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[STATUS]') AND type IN ('U'))
	DROP TABLE [dbo].[STATUS]
GO

CREATE TABLE [dbo].[STATUS] (
  [id] int  IDENTITY(1,1) NOT NULL,
  [name] varchar(15) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL
)
GO


-- ----------------------------
-- Records of STATUS
-- ----------------------------
BEGIN TRANSACTION
GO

SET IDENTITY_INSERT [dbo].[STATUS] ON
GO

INSERT INTO [dbo].[STATUS] ([id], [name]) VALUES (N'1', N'ingresado')
GO

INSERT INTO [dbo].[STATUS] ([id], [name]) VALUES (N'2', N'rechazado')
GO

INSERT INTO [dbo].[STATUS] ([id], [name]) VALUES (N'3', N'en curso')
GO

INSERT INTO [dbo].[STATUS] ([id], [name]) VALUES (N'4', N'cerrado')
GO

SET IDENTITY_INSERT [dbo].[STATUS] OFF
GO

COMMIT
GO


-- ----------------------------
-- Table structure for TICKET
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[TICKET]') AND type IN ('U'))
	DROP TABLE [dbo].[TICKET]
GO

CREATE TABLE [dbo].[TICKET] (
  [ticket_number] int  IDENTITY(1,1) NOT NULL,
  [type] varchar(15) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [description] varchar(200) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [creation_date] date  NOT NULL,
  [ending_date] date  NULL,
  [client_id] int  NULL,
  [status_id] int  NULL,
  [product_code] varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [composite_id] int  NULL,
  [quantity] int  NULL,
  [bill_id] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL
)
GO


-- ----------------------------
-- Records of TICKET
-- ----------------------------
BEGIN TRANSACTION
GO

SET IDENTITY_INSERT [dbo].[TICKET] ON
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'60', N'Cantidad', N'La cantidad entregada no fue la pactada se exceden en 2.', N'2018-11-20', NULL, N'5', N'1', N'P01', NULL, N'2', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'75', N'Cantidad', N'Se facturo incorrecto se envio fallido.', N'2018-11-21', NULL, N'7', N'3', N'P03', N'20', N'5', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'79', N'Cantidad', N'prueba 1', N'2018-11-24', N'2018-11-24', N'5', N'4', N'P04', N'26', N'5', N'')
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'65', N'Cantidad', N'La cantidad enviada es erronea y la entrega fue en sede Pilar, deseandose en CABA. El error se arrastra en la facturación. Se regeneran ordenes.', N'2018-11-20', N'2018-11-21', N'2', N'4', N'P05', N'17', N'0', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'88', N'Cantidad', N'Faltan entregar 2 TVs.', N'2018-11-26', NULL, N'4', N'1', N'9975', NULL, N'2', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'98', N'Cantidad', N'', N'2018-11-27', NULL, N'12', N'1', N'8762', NULL, N'0', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'102', N'Cantidad', N'Quisiera recibir vaso termicos para reventa.', N'2018-11-28', NULL, N'12', N'1', N'32404', NULL, N'10', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'73', N'Facturacion', N'Se facturo incorrecto se envio fallido.', N'2018-11-21', NULL, N'7', N'1', N'P03', N'20', N'5', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'61', N'Facturacion', N'Se facturaron 100$ extra en concepto de impuesto erroneamente, refacturar.', N'2018-11-16', N'2018-11-20', N'7', N'4', N'P03', NULL, N'0', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'71', N'Facturacion', N'Se facturo con un valor de envio incorrecto.', N'2018-11-19', N'2018-11-20', N'3', N'4', N'P06', N'19', N'0', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'67', N'Facturacion', N'La cantidad enviada es erronea y la entrega fue en sede Pilar, deseandose en CABA. El error se arrastra en la facturación. Se regeneran ordenes.', N'2018-11-20', NULL, N'2', N'3', N'P05', N'17', N'0', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'68', N'Facturacion', N'Se factura demas y faltan articulos en la entrega.', N'2018-11-20', NULL, N'3', N'1', N'P04', N'18', N'3', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'83', N'Facturacion', N'Factura invalida', N'2018-11-24', NULL, N'2', N'3', N'P05', NULL, N'0', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'91', N'Facturacion', N'Es necesario realizar nota de debito al comprador.', N'2018-11-26', NULL, N'7', N'1', N'32404', N'28', N'0', N'12343542342423968')
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'92', N'Facturacion', N'', N'2018-11-27', NULL, N'12', N'1', N'C0023', NULL, N'0', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'93', N'Facturacion', N'', N'2018-11-27', NULL, N'3', N'3', N'P01', NULL, N'0', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'94', N'Facturacion', N'Producto mal facturado. Por favor, evaluar.', N'2018-11-27', NULL, N'14', N'1', N'32404', NULL, N'0', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'95', N'Facturacion', N'Hubo errores en la facturación del producto TV 4k . ', N'2018-11-27', NULL, N'14', N'3', N'9975', NULL, N'0', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'96', N'Facturacion', N'Hubo errores en la facturación del producto TV 4k . ', N'2018-11-27', N'2018-11-27', N'14', N'4', N'9975', NULL, N'0', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'103', N'Facturacion', N'Me cobraron mas cantidad de televisores que la solicitada.', N'2018-11-28', NULL, N'17', N'1', N'9975', NULL, N'0', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'69', N'Faltante', N'Se factura demas y faltan articulos en la entrega.', N'2018-11-20', NULL, N'3', N'2', N'P04', N'18', N'3', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'78', N'Faltante', N'Se realiza el alta del reclamo se procede a revisión.', N'2018-11-24', NULL, N'7', N'3', N'P04', NULL, N'5', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'70', N'Faltante', N'El producto llego con unidades faltantes,', N'2018-11-20', NULL, N'5', N'3', N'P05', NULL, N'3', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'76', N'Faltante', N'Se facturo incorrecto se envio fallido.', N'2018-11-21', NULL, N'7', N'3', N'P03', N'20', N'5', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'80', N'Faltante', N'Prueba 2', N'2018-11-24', N'2018-11-24', N'5', N'4', N'P03', N'26', N'2', N'')
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'82', N'Faltante', N'prueba 4', N'2018-11-24', N'2018-11-27', N'5', N'4', N'P04', NULL, N'2', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'62', N'Faltante', N'Se manifieste un faltante detallado, se debe enviar el mismo.', N'2018-11-20', NULL, N'2', N'3', N'P04', NULL, N'5', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'87', N'Faltante', N'El proveedor envio 20 unidades MENOS que las solicitadas en la orden de compra original (OC 00000203047)', N'2018-11-25', N'2018-11-25', N'1', N'4', N'4040', N'27', N'30', N'')
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'89', N'Faltante', N'El cliente reclama el faltante de 10 aceites de primera marca.', N'2018-11-26', NULL, N'6', N'1', N'4040', NULL, N'10', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'90', N'Faltante', N'Faltan entregar 10 vasos termicos.-', N'2018-11-26', NULL, N'7', N'1', N'32404', N'28', N'10', N'')
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'97', N'Faltante', N'', N'2018-11-27', N'2018-11-27', N'12', N'4', N'P02', NULL, N'0', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'63', N'Producto', N'El producto enviado no es correcto, se deseaba caotrina', N'2018-11-20', NULL, N'6', N'3', N'P01', NULL, N'0', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'81', N'Producto', N'prueba 3', N'2018-11-24', N'2018-11-24', N'5', N'4', N'P05', N'26', N'0', N'')
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'86', N'Producto', N'El proveedor realizo entrega de aceite de segunda marca. La compra fue originalmente por el Aceite Marolio (primera marca).', N'2018-11-25', N'2018-11-25', N'1', N'4', N'4578', N'27', N'0', N'')
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'101', N'Producto', N'Quisiera recibir mochilas UA, he tenido muchos clientes preguntando por ellas.', N'2018-11-28', NULL, N'12', N'1', N'2035', NULL, N'30', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'66', N'Zona Entrega', N'La cantidad enviada es erronea y la entrega fue en sede Pilar, deseandose en CABA. El error se arrastra en la facturación. Se regeneran ordenes.', N'2018-11-20', NULL, N'2', N'3', N'P05', N'17', N'0', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'64', N'Zona Entrega', N'Se entrega en CABA erroneamente, reenviar a zona de cliente.', N'2018-11-20', N'2018-11-21', N'5', N'4', N'P02', NULL, N'0', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'77', N'Zona Entrega', N'Se entrega en zona incorrecta', N'2018-11-21', NULL, N'5', N'3', N'P04', NULL, N'0', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'74', N'Zona Entrega', N'Se facturo incorrecto se envio fallido.', N'2018-11-21', NULL, N'7', N'1', N'P03', N'20', N'5', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'72', N'Zona Entrega', N'Se facturo con un valor de envio incorrecto.', N'2018-11-20', N'2018-11-20', N'3', N'4', N'P06', N'19', N'0', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'84', N'Zona Entrega', N'El proveedor RES esta realizando operaciones comerciale en la zona de Munro. Por favor, tomar accion. Gracias.', N'2018-11-25', NULL, N'5', N'3', N'2035', NULL, N'0', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'85', N'Zona Entrega', N'El cliente ha entregado la mercaderia en una locacion distinta a la acordada. Gracias.', N'2018-11-25', N'2018-11-25', N'1', N'4', N'4040', N'27', N'0', N'')
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'99', N'Zona Entrega', N'El producto P01 se está entregando en una zona distinta a la acordada.', N'2018-11-27', NULL, N'12', N'3', N'P01', NULL, N'0', NULL)
GO

INSERT INTO [dbo].[TICKET] ([ticket_number], [type], [description], [creation_date], [ending_date], [client_id], [status_id], [product_code], [composite_id], [quantity], [bill_id]) VALUES (N'100', N'Zona Entrega', N'El cliente RES se encuentra vendiendo armarios en mi la zona asignada a mi negocio.', N'2018-11-28', NULL, N'12', N'1', N'8762', NULL, N'0', NULL)
GO

SET IDENTITY_INSERT [dbo].[TICKET] OFF
GO

COMMIT
GO


-- ----------------------------
-- Table structure for TICKET_COMPOSITE
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[TICKET_COMPOSITE]') AND type IN ('U'))
	DROP TABLE [dbo].[TICKET_COMPOSITE]
GO

CREATE TABLE [dbo].[TICKET_COMPOSITE] (
  [id] int  IDENTITY(1,1) NOT NULL,
  [creation_date] date  NOT NULL,
  [ending_date] date  NULL,
  [status_id] int  NULL,
  [client_id] int  NULL
)
GO


-- ----------------------------
-- Records of TICKET_COMPOSITE
-- ----------------------------
BEGIN TRANSACTION
GO

SET IDENTITY_INSERT [dbo].[TICKET_COMPOSITE] ON
GO

INSERT INTO [dbo].[TICKET_COMPOSITE] ([id], [creation_date], [ending_date], [status_id], [client_id]) VALUES (N'17', N'2018-11-20', NULL, N'1', N'2')
GO

INSERT INTO [dbo].[TICKET_COMPOSITE] ([id], [creation_date], [ending_date], [status_id], [client_id]) VALUES (N'18', N'2018-11-20', NULL, N'1', N'3')
GO

INSERT INTO [dbo].[TICKET_COMPOSITE] ([id], [creation_date], [ending_date], [status_id], [client_id]) VALUES (N'19', N'2018-11-20', N'2018-11-20', N'4', N'3')
GO

INSERT INTO [dbo].[TICKET_COMPOSITE] ([id], [creation_date], [ending_date], [status_id], [client_id]) VALUES (N'20', N'2018-11-21', NULL, N'1', N'7')
GO

INSERT INTO [dbo].[TICKET_COMPOSITE] ([id], [creation_date], [ending_date], [status_id], [client_id]) VALUES (N'21', N'2018-11-24', NULL, N'1', N'1')
GO

INSERT INTO [dbo].[TICKET_COMPOSITE] ([id], [creation_date], [ending_date], [status_id], [client_id]) VALUES (N'22', N'2018-11-24', NULL, N'1', N'4')
GO

INSERT INTO [dbo].[TICKET_COMPOSITE] ([id], [creation_date], [ending_date], [status_id], [client_id]) VALUES (N'23', N'2018-11-24', NULL, N'1', N'5')
GO

INSERT INTO [dbo].[TICKET_COMPOSITE] ([id], [creation_date], [ending_date], [status_id], [client_id]) VALUES (N'24', N'2018-11-24', NULL, N'1', N'5')
GO

INSERT INTO [dbo].[TICKET_COMPOSITE] ([id], [creation_date], [ending_date], [status_id], [client_id]) VALUES (N'25', N'2018-11-24', NULL, N'1', N'5')
GO

INSERT INTO [dbo].[TICKET_COMPOSITE] ([id], [creation_date], [ending_date], [status_id], [client_id]) VALUES (N'26', N'2018-11-24', N'2018-11-24', N'4', N'5')
GO

INSERT INTO [dbo].[TICKET_COMPOSITE] ([id], [creation_date], [ending_date], [status_id], [client_id]) VALUES (N'27', N'2018-11-25', N'2018-11-25', N'4', N'1')
GO

INSERT INTO [dbo].[TICKET_COMPOSITE] ([id], [creation_date], [ending_date], [status_id], [client_id]) VALUES (N'28', N'2018-11-26', NULL, N'1', N'7')
GO

SET IDENTITY_INSERT [dbo].[TICKET_COMPOSITE] OFF
GO

COMMIT
GO


-- ----------------------------
-- Table structure for TICKET_HISTORICAL
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[TICKET_HISTORICAL]') AND type IN ('U'))
	DROP TABLE [dbo].[TICKET_HISTORICAL]
GO

CREATE TABLE [dbo].[TICKET_HISTORICAL] (
  [id] int  IDENTITY(1,1) NOT NULL,
  [log] varchar(200) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [date] date  NOT NULL,
  [ticket_number] int  NULL,
  [user_id] int  NULL
)
GO


-- ----------------------------
-- Records of TICKET_HISTORICAL
-- ----------------------------
BEGIN TRANSACTION
GO

SET IDENTITY_INSERT [dbo].[TICKET_HISTORICAL] ON
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'71', N'', N'2018-11-20', N'72', N'5')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'76', N'', N'2018-11-21', N'77', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'77', N'', N'2018-11-21', N'64', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'88', N'', N'2018-11-24', N'75', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'89', N'', N'2018-11-24', N'76', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'90', N'', N'2018-11-24', N'78', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'91', N'', N'2018-11-24', N'75', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'92', N'', N'2018-11-24', N'69', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'93', N'', N'2018-11-24', N'69', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'94', N'', N'2018-11-24', N'69', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'100', N'', N'2018-11-24', N'63', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'101', N'', N'2018-11-24', N'82', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'102', N'', N'2018-11-24', N'75', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'103', N' > se cambia estado a Rechazado', N'2018-11-24', N'62', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'104', N' > se cambia estado a En curso', N'2018-11-24', N'62', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'110', N' > se cambia estado a En curso', N'2018-11-27', N'93', N'4')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'111', N' > se cambia estado a Cerrado', N'2018-11-27', N'97', N'7')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'112', N' > se cambia estado a Cerrado', N'2018-11-27', N'82', N'7')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'113', N' > se cambia estado a En curso', N'2018-11-28', N'99', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'69', N'', N'2018-11-20', N'61', N'4')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'70', N'', N'2018-11-20', N'71', N'4')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'72', N'', N'2018-11-21', N'67', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'73', N'', N'2018-11-21', N'65', N'7')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'74', N'', N'2018-11-21', N'77', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'75', N'', N'2018-11-21', N'77', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'78', N'', N'2018-11-24', N'79', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'79', N'', N'2018-11-24', N'79', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'80', N'', N'2018-11-24', N'80', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'81', N'', N'2018-11-24', N'81', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'82', N'', N'2018-11-24', N'63', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'83', N'', N'2018-11-24', N'62', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'84', N'', N'2018-11-24', N'69', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'85', N'', N'2018-11-24', N'76', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'86', N'', N'2018-11-24', N'62', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'105', N' > se cambia estado a En curso', N'2018-11-24', N'83', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'106', N' > se cambia estado a En curso', N'2018-11-25', N'84', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'67', N'', N'2018-11-20', N'70', N'7')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'68', N'', N'2018-11-20', N'63', N'7')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'107', N' > se cambia estado a Cerrado', N'2018-11-25', N'86', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'108', N' > se cambia estado a Cerrado', N'2018-11-25', N'85', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'109', N' > se cambia estado a Cerrado', N'2018-11-25', N'87', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'65', N'', N'2018-11-20', N'64', N'5')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'66', N'', N'2018-11-20', N'66', N'5')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'87', N'', N'2018-11-24', N'76', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'95', N'', N'2018-11-24', N'69', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'96', N'', N'2018-11-24', N'78', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'97', N'', N'2018-11-24', N'63', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'98', N'', N'2018-11-24', N'62', N'1')
GO

INSERT INTO [dbo].[TICKET_HISTORICAL] ([id], [log], [date], [ticket_number], [user_id]) VALUES (N'99', N'', N'2018-11-24', N'62', N'1')
GO

SET IDENTITY_INSERT [dbo].[TICKET_HISTORICAL] OFF
GO

COMMIT
GO


-- ----------------------------
-- Table structure for Users
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[Users]') AND type IN ('U'))
	DROP TABLE [dbo].[Users]
GO

CREATE TABLE [dbo].[Users] (
  [id] int  IDENTITY(1,1) NOT NULL,
  [user_name] varchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [pass] varchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL
)
GO


-- ----------------------------
-- Records of Users
-- ----------------------------
BEGIN TRANSACTION
GO

SET IDENTITY_INSERT [dbo].[Users] ON
GO

INSERT INTO [dbo].[Users] ([id], [user_name], [pass]) VALUES (N'1', N'all', N'all')
GO

INSERT INTO [dbo].[Users] ([id], [user_name], [pass]) VALUES (N'3', N'gdepina', N'salchicha')
GO

INSERT INTO [dbo].[Users] ([id], [user_name], [pass]) VALUES (N'4', N'fact', N'fact')
GO

INSERT INTO [dbo].[Users] ([id], [user_name], [pass]) VALUES (N'5', N'zona', N'zona')
GO

INSERT INTO [dbo].[Users] ([id], [user_name], [pass]) VALUES (N'6', N'callcenter', N'callcenter')
GO

INSERT INTO [dbo].[Users] ([id], [user_name], [pass]) VALUES (N'7', N'dist', N'dist')
GO

INSERT INTO [dbo].[Users] ([id], [user_name], [pass]) VALUES (N'8', N'consulta', N'consulta')
GO

INSERT INTO [dbo].[Users] ([id], [user_name], [pass]) VALUES (N'11', N'crusso', N'crusso')
GO

INSERT INTO [dbo].[Users] ([id], [user_name], [pass]) VALUES (N'12', N'asouto', N'roque')
GO

INSERT INTO [dbo].[Users] ([id], [user_name], [pass]) VALUES (N'13', N'usuario_call', N'usuario_call')
GO

INSERT INTO [dbo].[Users] ([id], [user_name], [pass]) VALUES (N'14', N'usuario_fact', N'usuario_fact')
GO

INSERT INTO [dbo].[Users] ([id], [user_name], [pass]) VALUES (N'15', N'usuario_dist', N'usuario_dist')
GO

INSERT INTO [dbo].[Users] ([id], [user_name], [pass]) VALUES (N'16', N'usuario_cons', N'usuario_cons')
GO

INSERT INTO [dbo].[Users] ([id], [user_name], [pass]) VALUES (N'17', N'usuario_zona', N'usuario_zona')
GO

SET IDENTITY_INSERT [dbo].[Users] OFF
GO

COMMIT
GO


-- ----------------------------
-- Table structure for ZONE
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[ZONE]') AND type IN ('U'))
	DROP TABLE [dbo].[ZONE]
GO

CREATE TABLE [dbo].[ZONE] (
  [zone_code] int  NOT NULL,
  [name] varchar(40) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL
)
GO


-- ----------------------------
-- Records of ZONE
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[ZONE]  VALUES (N'1', N'Norte')
GO

INSERT INTO [dbo].[ZONE]  VALUES (N'2', N'Centro')
GO

INSERT INTO [dbo].[ZONE]  VALUES (N'3', N'Sur')
GO

INSERT INTO [dbo].[ZONE]  VALUES (N'4', N'Oeste')
GO

INSERT INTO [dbo].[ZONE]  VALUES (N'5', N'Este')
GO

INSERT INTO [dbo].[ZONE]  VALUES (N'9', N'Pilar')
GO

INSERT INTO [dbo].[ZONE]  VALUES (N'10', N'CABA')
GO

INSERT INTO [dbo].[ZONE]  VALUES (N'6', N'V. Luzuriaga')
GO

INSERT INTO [dbo].[ZONE]  VALUES (N'7', N'Vicente Lopez')
GO

INSERT INTO [dbo].[ZONE]  VALUES (N'8', N'Munro')
GO

COMMIT
GO


-- ----------------------------
-- Primary Key structure for table Client
-- ----------------------------
ALTER TABLE [dbo].[Client] ADD CONSTRAINT [PK__Client__3213E83F328C0BF5] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
GO


-- ----------------------------
-- Primary Key structure for table Product
-- ----------------------------
ALTER TABLE [dbo].[Product] ADD CONSTRAINT [PK__Product__AE1A8CC564984633] PRIMARY KEY CLUSTERED ([product_code])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
GO


-- ----------------------------
-- Primary Key structure for table STATUS
-- ----------------------------
ALTER TABLE [dbo].[STATUS] ADD CONSTRAINT [PK__STATUS__3213E83FFB7E68A9] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
GO


-- ----------------------------
-- Indexes structure for table TICKET
-- ----------------------------
CREATE CLUSTERED INDEX [IX_TICKET_TYPE]
ON [dbo].[TICKET] (
  [type] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table TICKET_COMPOSITE
-- ----------------------------
ALTER TABLE [dbo].[TICKET_COMPOSITE] ADD CONSTRAINT [PK__TICKET_C__3213E83F3A90D67B] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
GO


-- ----------------------------
-- Primary Key structure for table Users
-- ----------------------------
ALTER TABLE [dbo].[Users] ADD CONSTRAINT [PK__Users__3213E83F3331ED8E] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
GO


-- ----------------------------
-- Foreign Keys structure for table TICKET
-- ----------------------------
ALTER TABLE [dbo].[TICKET] ADD CONSTRAINT [FK__TICKET__client_i__5FB337D6] FOREIGN KEY ([client_id]) REFERENCES [dbo].[Client] ([id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[TICKET] ADD CONSTRAINT [FK__TICKET__product___60A75C0F] FOREIGN KEY ([product_code]) REFERENCES [dbo].[Product] ([product_code]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[TICKET] ADD CONSTRAINT [FK__TICKET__composit__6383C8BA] FOREIGN KEY ([composite_id]) REFERENCES [dbo].[TICKET_COMPOSITE] ([id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[TICKET] ADD CONSTRAINT [FK__TICKET__status_i__66603565] FOREIGN KEY ([status_id]) REFERENCES [dbo].[STATUS] ([id]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

