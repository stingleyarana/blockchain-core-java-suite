import hashlib
import ecdsa
import binascii

def sha256_hash(data: str) -> str:
    return hashlib.sha256(data.encode()).hexdigest()

def generate_ecdsa_key():
    sk = ecdsa.SigningKey.generate(curve=ecdsa.SECP256k1)
    vk = sk.get_verifying_key()
    private_key = binascii.hexlify(sk.to_string()).decode()
    public_key = binascii.hexlify(vk.to_string()).decode()
    return private_key, public_key

def sign_data(private_key_hex: str, data: str) -> str:
    sk = ecdsa.SigningKey.from_string(binascii.unhexlify(private_key_hex), curve=ecdsa.SECP256k1)
    signature = sk.sign(data.encode())
    return binascii.hexlify(signature).decode()

def verify_signature(public_key_hex: str, data: str, signature_hex: str) -> bool:
    vk = ecdsa.VerifyingKey.from_string(binascii.unhexlify(public_key_hex), curve=ecdsa.SECP256k1)
    try:
        return vk.verify(binascii.unhexlify(signature_hex), data.encode())
    except:
        return False
