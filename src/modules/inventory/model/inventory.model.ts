import mongoose, { Mongoose } from "mongoose";

export interface IInventory {
  foodType: string;
  category: string;
  subCategory: string;
  itemName: string;
  sellerPrice: number;
  inboxPrice: number;
  isActive: boolean;
  userId: mongoose.Schema.Types.ObjectId;
  showItem: boolean;
}

const InventorySchema = new mongoose.Schema<IInventory>(
  {
    foodType: {
      type: String,
      required: true,
    },
    category: {
      type: String,
      required: true,
    },
    subCategory: {
      type: String,
      required: true,
    },
    itemName: {
      type: String,
      required: true,
    },
    sellerPrice: {
      type: Number,
      required: true,
    },
    inboxPrice: {
      type: Number,
      default: 0,
    },
    isActive: {
      type: Boolean,
      required: true,
    },
    userId: {
      type: mongoose.Schema.Types.ObjectId,
      ref: "menu-users",
    },
    showItem: {
      type: Boolean,
      default: false,
    },
  },
  {
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

const inventory = mongoose.model<IInventory>("menu-inventory", InventorySchema);

export { inventory };
