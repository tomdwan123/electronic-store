BEGIN TRY
BEGIN TRANSACTION

    CREATE TABLE [role]
    (
        [id] [bigint] IDENTITY(1, 1) NOT NULL,
        [name] [nvarchar](255) NOT NULL,
        [create_date] [datetime] NULL,
        [create_by] [bigint] NULL,
        [update_date] [datetime] NULL,
        [update_by] [bigint] NULL,
        [version] [bigint] NULL,
        CONSTRAINT [PK_role]
        PRIMARY KEY ([id] ASC)
    );

    CREATE TABLE [user]
    (
        [id] [bigint] IDENTITY(1, 1) NOT NULL,
        [first_name] [nvarchar](255) NOT NULL,
        [last_name] [nvarchar](255) NOT NULL,
        [email] [nvarchar](255) NOT NULL,
        [create_date] [datetime] NULL,
        [create_by] [bigint] NULL,
        [update_date] [datetime] NULL,
        [update_by] [bigint] NULL,
        [version] [bigint] NULL,
        CONSTRAINT [PK_user]
        PRIMARY KEY ([id] ASC)
    );

    CREATE TABLE [product]
    (
        [id] [bigint] IDENTITY(1, 1) NOT NULL,
        [name] [nvarchar](255) NOT NULL,
        [price] [double] NOT NULL,
        [create_date] [datetime] NULL,
        [create_by] [bigint] NULL,
        [update_date] [datetime] NULL,
        [update_by] [bigint] NULL,
        [version] [bigint] NULL,
        CONSTRAINT [PK_product]
        PRIMARY KEY ([id] ASC)
    );

    CREATE TABLE [discount_deal]
    (
        [id] [bigint] IDENTITY(1, 1) NOT NULL,
        [description] [nvarchar](255) NOT NULL,
        [create_date] [datetime] NULL,
        [create_by] [bigint] NULL,
        [update_date] [datetime] NULL,
        [update_by] [bigint] NULL,
        [version] [bigint] NULL,
        CONSTRAINT [PK_discount_deal]
        PRIMARY KEY ([id] ASC)
    );

    CREATE TABLE [user_role]
    (
        [user_id] [bigint] IDENTITY(1, 1) NOT NULL,
        [role_id] [bigint] IDENTITY(1, 1) NOT NULL,
        [create_date] [datetime] NULL,
        [create_by] [bigint] NULL,
        [update_date] [datetime] NULL,
        [update_by] [bigint] NULL,
        [version] [bigint] NULL,
        CONSTRAINT [PK_user_role]
        PRIMARY KEY
        (
            [user_id] ASC, [role_id] ASC
        )
    );

    CREATE TABLE [basket]
    (
        [user_id] [bigint] IDENTITY(1, 1) NOT NULL,
        [product_id] [bigint] IDENTITY(1, 1) NOT NULL,
        [quantity] [bigint] IDENTITY(1, 1) NOT NULL,
        [create_date] [datetime] NULL,
        [create_by] [bigint] NULL,
        [update_date] [datetime] NULL,
        [update_by] [bigint] NULL,
        [version] [bigint] NULL,
        CONSTRAINT [PK_basket]
        PRIMARY KEY
        (
            [user_id] ASC, [product_id] ASC
        )
    );

    CREATE TABLE [product_discount_deal]
    (
        [product_id] [bigint] IDENTITY(1, 1) NOT NULL,
        [discount_deal_id] [bigint] IDENTITY(1, 1) NOT NULL,
        [create_date] [datetime] NULL,
        [create_by] [bigint] NULL,
        [update_date] [datetime] NULL,
        [update_by] [bigint] NULL,
        [version] [bigint] NULL,
        CONSTRAINT [PK_product_discount_deal]
        PRIMARY KEY
        (
            [product_id] ASC, [discount_deal_id] ASC
        )
    );

    ALTER TABLE [role]
        ADD CONSTRAINT [UK_role__name]
        UNIQUE ([name] ASC);

    ALTER TABLE [user]
        ADD CONSTRAINT [UK_user__email]
        UNIQUE ([email] ASC);

    ALTER TABLE [product]
        ADD CONSTRAINT [UK_product__name]
        UNIQUE ([name] ASC);

    ALTER TABLE [user_role] WITH CHECK
        ADD CONSTRAINT [FK_user_role__user]
        FOREIGN KEY ([user_id])
        REFERENCES [user] ([id]);
    ALTER TABLE [user_role] CHECK CONSTRAINT [FK_user_role__user];

    ALTER TABLE [user_role] WITH CHECK
        ADD CONSTRAINT [FK_user_role__role]
        FOREIGN KEY ([role_id])
        REFERENCES [role] ([id]);
    ALTER TABLE [user_role] CHECK CONSTRAINT [FK_user_role__role];

    ALTER TABLE [basket] WITH CHECK
        ADD CONSTRAINT [FK_basket__user]
        FOREIGN KEY ([user_id])
        REFERENCES [user] ([id]);
    ALTER TABLE [basket] CHECK CONSTRAINT [FK_basket__user];

    ALTER TABLE [basket] WITH CHECK
        ADD CONSTRAINT [FK_basket__product]
        FOREIGN KEY ([product_id])
        REFERENCES [product] ([id]);
    ALTER TABLE [basket] CHECK CONSTRAINT [FK_basket__product];

    ALTER TABLE [product_discount_deal] WITH CHECK
        ADD CONSTRAINT [FK_product_discount_deal__product]
        FOREIGN KEY ([product_id])
        REFERENCES [product] ([id]);
    ALTER TABLE [product_discount_deal] CHECK CONSTRAINT [FK_product_discount_deal__product];

    ALTER TABLE [product_discount_deal] WITH CHECK
        ADD CONSTRAINT [FK_product_discount_deal__discount_deal]
        FOREIGN KEY ([discount_deal_id])
        REFERENCES [discount_deal] ([id]);
    ALTER TABLE [product_discount_deal] CHECK CONSTRAINT [FK_product_discount_deal__discount_deal];

COMMIT TRANSACTION
END TRY

BEGIN CATCH
IF @@TRANCOUNT > 0
        ROLLBACK TRANSACTION
    PRINT 'An error occurred: ' + ERROR_MESSAGE();
END CATCH