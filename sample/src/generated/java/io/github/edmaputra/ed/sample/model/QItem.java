package io.github.edmaputra.ed.sample.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = -587687469L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItem item = new QItem("item");

    public final io.github.edmaputra.ed.edpos.model.QBaseItem _super;

    public final QCategory category;

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

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    //inherited
    public final StringPath name;

    //inherited
    public final StringPath note;

    //inherited
    public final StringPath updater;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updateTime;

    //inherited
    public final StringPath version;

    public QItem(String variable) {
        this(Item.class, forVariable(variable), INITS);
    }

    public QItem(Path<? extends Item> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItem(PathMetadata metadata, PathInits inits) {
        this(Item.class, metadata, inits);
    }

    public QItem(Class<? extends Item> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new io.github.edmaputra.ed.edpos.model.QBaseItem(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.createTime = _super.createTime;
        this.creator = _super.creator;
        this.deleteBy = _super.deleteBy;
        this.deleteFlag = _super.deleteFlag;
        this.deleteTime = _super.deleteTime;
        this.name = _super.name;
        this.note = _super.note;
        this.updater = _super.updater;
        this.updateTime = _super.updateTime;
        this.version = _super.version;
    }

}

