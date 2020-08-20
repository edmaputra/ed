package io.github.edmaputra.ed.sample.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemDetail is a Querydsl query type for ItemDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItemDetail extends EntityPathBase<ItemDetail> {

    private static final long serialVersionUID = -269241340L;

    public static final QItemDetail itemDetail = new QItemDetail("itemDetail");

    public final io.github.edmaputra.ed.edpos.model.QBaseItemDetail _super = new io.github.edmaputra.ed.edpos.model.QBaseItemDetail(this);

    //inherited
    public final StringPath barcode = _super.barcode;

    //inherited
    public final NumberPath<java.math.BigDecimal> costPrice = _super.costPrice;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createTime = _super.createTime;

    //inherited
    public final StringPath creator = _super.creator;

    //inherited
    public final StringPath deleteBy = _super.deleteBy;

    //inherited
    public final BooleanPath deleteFlag = _super.deleteFlag;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> deleteTime = _super.deleteTime;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> expiryDate = _super.expiryDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath note = _super.note;

    //inherited
    public final NumberPath<java.math.BigDecimal> sellPrice = _super.sellPrice;

    //inherited
    public final NumberPath<Integer> stock = _super.stock;

    //inherited
    public final NumberPath<Integer> stockAlert = _super.stockAlert;

    //inherited
    public final StringPath stockKeepingUnit = _super.stockKeepingUnit;

    //inherited
    public final StringPath updater = _super.updater;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updateTime = _super.updateTime;

    //inherited
    public final StringPath variant = _super.variant;

    //inherited
    public final StringPath version = _super.version;

    public QItemDetail(String variable) {
        super(ItemDetail.class, forVariable(variable));
    }

    public QItemDetail(Path<? extends ItemDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemDetail(PathMetadata metadata) {
        super(ItemDetail.class, metadata);
    }

}

