import { ISellers } from "../model/seller.model";
import { Sellers } from "../model/seller.model";
import {sellerValidation, loginValidation} from "../validator/seller.validator";
import { randomBytes, randomInt } from "crypto";
import { createToken, CustomRequest } from "../../../middleware/auth/auth";
import { Password } from "../../../utils/password";
import { badRequestError } from "../../../middleware/errorResponse";
import { successResponse } from "@agnimura/common";


const registration = async (req: Request, res: Response) => {
    try {
        const validateData: ISellers = req.body;
        const details = await Sellers.findOne({ email: validateData.email });
        if (details) return badRequestError("Email already in use", {}, res);
        const validate: any = sellerValidation(validateData);
        if (validate?.error?.details?.length > 0) return badRequestError('somefields are missing!', validate?.error?.details, res);
        const passwordGeneration = randomBytes(4).toString('hex');
        const password: string = await Password.toHash(passwordGeneration);
        validateData.password = password;
        const respData = new Sellers(validateData);
        const Da = await respData.save();
        return successResponse(201, "data saved successfully", respData, res);

    } catch (err: any) {
        console.log(err)
        return badRequestError("failed to save database", err, res);
    }
}




const login = async (req: Request, res: Response) => {
    try {
        let obj: any = loginValidation(req.body);
        let validate = obj.value;
        if (validate?.error?.details?.length > 0) return badRequestError('somefields are missing!', validate?.error?.details, res);
        const details: any = await Sellers.findOne({ email: validate.email });
        console.log(details, validate)
        if (!details) return badRequestError("Email Not found", details, res);
        const passwordCompare = await Password.compare(details.password, obj.password);
        if (passwordCompare && details.status) {
            const tokenData: ITokenData = {
                id: details.id,
                email: details.email,
                role: details.role
            };
            const token = await createToken(tokenData);
            const resObj: { email: string, role: string, token: string } = {
                email: details.email,
                token: token
            }
            return successResponse(200, 'logged in successfully', resObj, res);
        } else {
            return badRequestError("Password mismatch!", {}, res)
        }
    } catch (err) {
        console.log(err)
        return badRequestError("Something went wrong!", err, res);
    }
}




const getAllSeller = async (req: Request, res: Response) => {
    try {
        const data = await Sellers.find();
        return successResponse(200, "Sellers list fetched successfully0", data, res)
    } catch (err) {
        return badRequestError("Something went wrong!", err, res);
    }
}




const getSellerById = async (req: Request, res: Response) => {
    try {
        const details = await Sellers.findById({ _id: req?.params?.sellerId });
        return !details ?  badRequestError("seller not found", {}, res) :   successResponse(200, "seller details", details, res);

    } catch (err) {
        return badRequestError("Something went wrong!", err, res);
    }
}



const updateSellerBy = async (req: Request, res: Response) => {
    try {
        const id: string = req.params.sellerId;
        const reqData = req.body;
        const details = await Sellers.findById({ _id: id });
        if (!details) return badRequestError("Seller not found", {}, res);
        const updateUser = await Sellers.findByIdAndUpdate(
            { _id: id },
            { $set: reqData }
        );
        return successResponse(200, "Seller is successfully updated", [], res);
    } catch (err) {
        return badRequestError("Something went wrong!", err, res);
    }
};



export {registration, login, getAllSeller, getSellerById, updateSellerBy};