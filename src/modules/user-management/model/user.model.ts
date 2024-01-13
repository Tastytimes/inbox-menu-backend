import mongoose from "mongoose";


export interface IUsers {
    fullName: string;
    email: string;
    password?: string;
    role: string;
    status: boolean;
};

const UserSchema = new mongoose.Schema<IUsers>({
    fullName: {
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
    role: {
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

const Users = mongoose.model<IUsers>("menu-users", UserSchema);
export { Users };