import * as Jwt from "jsonwebtoken";
import { ITokenData } from "./auth.interface";
import Environment from "../../config/global.config";
import { NextFunction, Request, Response } from "express";

export interface CustomRequest extends Request {
    token: string | Jwt.JwtPayload;
    id: string;
    role: string;
}

const createToken = async (data: ITokenData) => {
    const env = Environment.ADMIN_TOKEN_JWT_KEY;
    const token = Jwt.sign({ id: data.id, role: data.role }, env, {
        expiresIn: 60 * 60 * 24 * 7,
    });
    return token;
};

const adminTokenDecode = async (
    req: Request,
    res: Response,
    next: NextFunction
) => {
    const env = Environment.ADMIN_TOKEN_JWT_KEY;
    const token = req.header("token")?.replace("Bearer ", "");
    try {
        if (!token) {
            return res.send("Token not found");
        }
        const decoded = Jwt.verify(token, env);
        (req as CustomRequest).token = decoded;
        next();
    } catch (err) {
        return res.status(403).json({
            message: "Invalid token",
        });
    }
};

export { createToken, adminTokenDecode };
