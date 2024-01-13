import Joi from "joi";
import { IInventory } from "../model/inventory.model";
const inventoryValidation = (regData: IInventory) => {
  const dataSchema = Joi.object({
    foodType: Joi.string().required(),
    category: Joi.string().required(),
    subCategory: Joi.string().required(),
    itemName: Joi.string().required(),
    sellerPrice: Joi.number().required(),
    isActive: Joi.boolean().required(),
  });
  return dataSchema.validate(regData, { abortEarly: false });
};

export { inventoryValidation };
