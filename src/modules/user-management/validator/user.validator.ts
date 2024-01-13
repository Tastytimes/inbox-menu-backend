import Joi from "joi";
import { IUsers } from "../model/user.model";
import { loginReq } from "../model/user.interface";


const uservalidation = (regData: IUsers) => {
    const dataSchema = Joi.object({
        fullName: Joi.string().required(),
        email: Joi.string().required(),
        role: Joi.string().required(),
        status: Joi.boolean().required()
    });
    return dataSchema.validate(regData, { abortEarly: false });
}

const loginValidation = (loginData: loginReq) => {
    const dataSchema = Joi.object({
        email: Joi.string().required(),
        password: Joi.string().required(),
    })
    return dataSchema.validate(loginData, { abortEarly: false });
}

export { uservalidation, loginValidation }