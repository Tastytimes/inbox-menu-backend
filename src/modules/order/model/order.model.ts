// import mongoose, { Mongoose } from "mongoose";




// export interface IOrders {
//     kitchenName: string;
//     email: string;
//     status: boolean;
// };

// const inventrySchema = new Schema({
//     in: {
//       type: String
//     },
//     subcategory: {
//       type: String
//     },
//     difficulty: {
//       type: Number,
//       default: 0
//     },
//     lat: {
//       type: Number
//     },
//     lng: {
//       type: Number
//     }
//   })

// const OrderSchema = new mongoose.Schema<IOrders>({
//     userName: {
//         type: String,
//         required: true
//     },
//     userEmail: {
//         type: String,
//         required: true
//     },
//     userPhone: {
//         type: String,
//         required: true
//     },
//     sellerId: {

//     },
//     orders: [inventrySchema]
// }, {
//     timestamps: true,
//     toJSON: {
//         transform(doc, ret) {
//             ret.id = ret._id;
//             delete ret._id;
//             delete ret.__v;
//         },
//     },
// }
// );

// const Orders = mongoose.model<IOrders>("menu-orders", OrderSchema);
// export { Orders };
