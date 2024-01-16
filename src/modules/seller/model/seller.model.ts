import mongoose, { Mongoose } from "mongoose";




export interface ISellers {
    kitchenName: string;
    email: string;
    status: boolean;
};

const SellersSchema = new mongoose.Schema<ISellers>({
    kitchenName: {
        type: String,
        required: true
    },
    email: {
        type: String,
        required: true
    },
    password: {
        type: String,
        required: true
    },
    status: {
        type: Boolean,
        required: true
    }
}, {
    timestamps: true,
    toJSON: {
        transform(doc, ret) {
            ret.id = ret._id;
            delete ret._id;
            delete ret.__v;
        },
    },
}
);

const Sellers = mongoose.model<IUsers>("menu-seller", SellersSchema);
export { Sellers };
