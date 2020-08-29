package io.github.edmaputra.ed.sample.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemDetail is a Querydsl query type for ItemDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItemDetail extends EntityPathBase<ItemDetail> {

    private static final long serialVersionUID = -269241340L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemDetail itemDetail = new QItemDetail("itemDetail");

    public final io.github.edmaputra.ed.edpos.model.QBaseItemDetail _super;

    //inherited
    public final StringPath barcode;

    //inherited
    public final NumberPath<java.math.BigDecimal> costPrice;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createTime;

    //inherited
    public final StringPath creator;

    //inherited
    public final StringPath deleteBy;

    //inherited
    public final BooleanPath deleteFlag;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> deleteTime;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> expiryDate;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final QItem item;

    //inherited
    public final StringPath note;

    //inherited
    public final NumberPath<java.math.BigDecimal> sellPrice;

    //inherited
    public final NumberPath<Integer> stock;

    //inherited
    public final NumberPath<Integer> stockAlert;

    //inherited
    public final StringPath stockKeepingUnit;

    //inherited
    public final StringPath updater;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updateTime;

    //inherited
    public final StringPath variant;

    //inherited
    public final StringPath version;

    public QItemDetail(String variable) {
        this(ItemDetail.class, forVariable(variable), INITS);
    }

    public QItemDetail(Path<? extends ItemDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemDetail(PathMetadata metadata, PathInits inits) {
        this(ItemDetail.class, metadata, inits);
    }

    public QItemDetail(Class<? extends ItemDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new io.github.edmaputra.ed.edpos.model.QBaseItemDetail(type, metadata, inits);
        this.barcode = _super.barcode;
        this.costPrice = _super.costPrice;
        this.createTime = _super.createTime;
        this.creator = _super.creator;
        this.deleteBy = _super.deleteBy;
        this.deleteFlag = _super.deleteFlag;
        this.deleteTime = _super.deleteTime;
        this.expiryDate = _super.expiryDate;
        this.item = inits.isInitialized("item") ? new QItem(forProperty("item")) : null;
        this.note = _super.note;
        this.sellPrice = _super.sellPrice;
        this.stock = _super.stock;
        this.stockAlert = _super.stockAlert;
        this.stockKeepingUnit = _super.stockKeepingUnit;
        this.updater = _super.updater;
        this.updateTime = _super.updateTime;
        this.variant = _super.variant;
        this.version = _super.version;
    }

}

